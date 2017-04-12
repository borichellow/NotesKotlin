package com.example.boris.noteskotlin

import android.annotation.SuppressLint
import android.content.Context
import com.appunite.rx.functions.Functions1
import com.appunite.rx.operators.MoreOperators
import com.example.boris.noteskotlin.dagger.ForApplication
import com.example.boris.noteskotlin.model.NoteItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import rx.Observable
import rx.subjects.PublishSubject

class UserPreferences(@ForApplication context: Context, val gson: Gson) {

    private val PREFERENCES_NAME = "user_preferences"
    private val ALL_NOTES = "all_notes"

    val notesInfoRefresh = PublishSubject.create<Any>()!!

    val preferences = context.getSharedPreferences(PREFERENCES_NAME, 0)!!

    @SuppressLint("CommitPrefEdits")
    fun addPassengerInfo(noteItem: NoteItem) {
        val json: String? = preferences.getString(ALL_NOTES, null)
        val type = object : TypeToken<List<NoteItem>>() {}.type

        val list = gson.fromJson<ArrayList<NoteItem>>(json, type) ?: ArrayList<NoteItem>()
        list.add(noteItem)

        val newJson = gson.toJson(list)
        preferences.edit().putString(ALL_NOTES, newJson).commit()
        notesInfoRefresh.onNext(Any())
    }

    private fun getAllNotes(): List<NoteItem> {
        val json = preferences.getString(ALL_NOTES, null)
        val type = object : TypeToken<List<NoteItem>>() {}.type

        return gson.fromJson(json, type) ?: ArrayList()
    }

    fun getAllNotesObservable(): Observable<List<NoteItem>> {
        return Observable
                .defer<List<NoteItem>> {
                    Observable.just<List<NoteItem>>(getAllNotes())
                            .filter(Functions1.isNotNull())
                }
                .compose<List<NoteItem>>(MoreOperators.refresh<List<NoteItem>>(notesInfoRefresh))
    }
}
