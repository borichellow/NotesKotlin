package com.example.boris.noteskotlin.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.appunite.rx.android.adapter.UniversalAdapter
import com.example.boris.noteskotlin.MainApplication
import com.example.boris.noteskotlin.R
import com.example.boris.noteskotlin.base.BaseActivity
import com.example.boris.noteskotlin.dagger.ActivityModule
import com.jakewharton.rxbinding.view.RxView
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val adapter = UniversalAdapter(listOf(
//                connectionItemManager, fakeItemManager
//        ))

        val forecastList = findViewById(R.id.main_notes_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)
//        forecastList.adapter = adapter
        forecastList.setHasFixedSize(true)
//        forecastList.adapter = ForecastListAdapter(items)

        RxView.clicks(forecastList)
    }

    override fun initDagger() {
        val component = DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .appComponent(MainApplication.component)
                .build()
        component.inject(this)
    }
}
