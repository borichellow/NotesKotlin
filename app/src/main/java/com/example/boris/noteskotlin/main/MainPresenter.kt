package com.example.boris.noteskotlin.main

import com.appunite.rx.android.adapter.BaseAdapterItem
import com.example.boris.noteskotlin.UserPreferences
import com.example.boris.noteskotlin.model.NoteItem
import rx.Observable
import rx.Observer
import rx.observers.Observers
import rx.subjects.PublishSubject
import javax.inject.Inject

class MainPresenter @Inject constructor(userPreferences: UserPreferences) {

    val noteListObservable: Observable<List<BaseAdapterItem>>
    val openNoteSubject: PublishSubject<Long> = PublishSubject.create()

    init {
        this.noteListObservable = userPreferences
                .getAllNotesObservable()
                .map({ list ->
                    val out = ArrayList<BaseAdapterItem>()
                    list.mapTo(out) { NoteAdapterItem(it) }
                })
    }

    inner class NoteAdapterItem(val noteItem: NoteItem) : BaseAdapterItem {

        fun clickObserver(): Observer<Any> {
            return Observers.create { openNoteSubject.onNext(noteItem.id) }
        }

        override fun adapterId(): Long {
            return noteItem.id
        }

        override fun same(p0: BaseAdapterItem): Boolean {
            if (p0 !is NoteAdapterItem) return false
            val that = p0 as NoteAdapterItem
            return that.noteItem == this.noteItem
        }

        override fun matches(p0: BaseAdapterItem): Boolean {
            return p0.adapterId() == this.adapterId()
        }

    }
}