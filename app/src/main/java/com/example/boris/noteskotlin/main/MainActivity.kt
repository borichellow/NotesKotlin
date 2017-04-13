package com.example.boris.noteskotlin.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.appunite.rx.android.adapter.UniversalAdapter
import com.example.boris.noteskotlin.MainApplication
import com.example.boris.noteskotlin.R
import com.example.boris.noteskotlin.base.BaseActivity
import com.example.boris.noteskotlin.dagger.ActivityComponent
import com.example.boris.noteskotlin.dagger.ActivityModule
import com.example.boris.noteskotlin.dagger.DaggerActivityComponent
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.activity_main.*
import rx.subscriptions.SerialSubscription
import rx.subscriptions.Subscriptions
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var presenter: MainPresenter
    @Inject
    lateinit var noteItemManager: NoteItemManager

    val subscription = SerialSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = UniversalAdapter(listOf(noteItemManager))

        main_notes_list.layoutManager = LinearLayoutManager(this)
        main_notes_list.adapter = adapter as RecyclerView.Adapter<*>?
        main_notes_list.setHasFixedSize(true)

        subscription.set(Subscriptions.from(
                RxView.clicks(new_note)
                        .subscribe({ Toast.makeText(this, "not implemented yet", Toast.LENGTH_LONG).show() }),
                presenter.noteListObservable
                        .subscribe(adapter),
                presenter.openNoteSubject
                        .subscribe({ Toast.makeText(this, "not implemented yet", Toast.LENGTH_LONG).show() })

        ))


    }

    override fun onDestroy() {
        super.onDestroy()
        subscription.set(Subscriptions.empty())
    }

    override fun initDagger() {
        val component: ActivityComponent = DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .appComponent(MainApplication.component)
                .build()
        component.inject(this)
    }
}
