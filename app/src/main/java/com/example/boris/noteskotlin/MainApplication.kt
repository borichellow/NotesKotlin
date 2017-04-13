package com.example.boris.noteskotlin

import android.app.Application
import com.example.boris.noteskotlin.dagger.AppComponent
import com.example.boris.noteskotlin.dagger.AppModule
import com.example.boris.noteskotlin.dagger.DaggerAppComponent

class MainApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        component.inject(this)
    }

}