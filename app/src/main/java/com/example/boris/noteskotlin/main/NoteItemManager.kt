package com.example.boris.noteskotlin.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appunite.rx.android.adapter.BaseAdapterItem
import com.appunite.rx.android.adapter.ViewHolderManager
import com.example.boris.noteskotlin.R
import rx.subscriptions.SerialSubscription
import rx.subscriptions.Subscriptions
import javax.inject.Inject

class NoteItemManager @Inject constructor() : ViewHolderManager {

    override fun createViewHolder(viewGroup: ViewGroup, layoutInflater: LayoutInflater): ViewHolderManager.BaseViewHolder<*> {
        return Holder(layoutInflater.inflate(R.layout.note_cell_item, viewGroup, false))
    }

    override fun matches(baseAdapterItem: BaseAdapterItem): Boolean {
        return baseAdapterItem is MainPresenter.NoteAdapterItem
    }

    inner class Holder(view: View) : ViewHolderManager.BaseViewHolder<MainPresenter.NoteAdapterItem>(view) {

        val subscription = SerialSubscription()

        override fun onViewRecycled() {
            super.onViewRecycled()
            subscription.set(Subscriptions.empty())
        }

        override fun bind(noteItem: MainPresenter.NoteAdapterItem) {

        }

    }
}