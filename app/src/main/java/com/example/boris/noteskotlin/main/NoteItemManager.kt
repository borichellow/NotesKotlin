package com.example.boris.noteskotlin.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appunite.rx.android.adapter.BaseAdapterItem
import com.appunite.rx.android.adapter.ViewHolderManager
import com.example.boris.noteskotlin.R
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.note_cell_item.view.*
import rx.subscriptions.SerialSubscription
import rx.subscriptions.Subscriptions
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NoteItemManager @Inject constructor() : ViewHolderManager {

    override fun createViewHolder(viewGroup: ViewGroup, layoutInflater: LayoutInflater): ViewHolderManager.BaseViewHolder<*> {
        return Holder(layoutInflater.inflate(R.layout.note_cell_item, viewGroup, false))
    }

    override fun matches(baseAdapterItem: BaseAdapterItem): Boolean {
        return baseAdapterItem is MainPresenter.NoteAdapterItem
    }

    inner class Holder(val view: View) : ViewHolderManager.BaseViewHolder<MainPresenter.NoteAdapterItem>(view) {

        val subscription = SerialSubscription()

        override fun onViewRecycled() {
            super.onViewRecycled()
            subscription.set(Subscriptions.empty())
        }

        override fun bind(noteItem: MainPresenter.NoteAdapterItem) {
            view.note_item_title.text = noteItem.noteItem.title
            view.note_item_date.text = SimpleDateFormat("MMM d 'at' h:mm a", Locale.getDefault()).format(noteItem.noteItem.date)

            subscription.set(Subscriptions.from(
                    RxView.clicks(view)
                            .subscribe(noteItem.clickObserver())
            ))
        }

    }
}