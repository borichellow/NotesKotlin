package com.example.boris.noteskotlin.dagger

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.example.boris.noteskotlin.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module class ActivityModule(val activity: BaseActivity) {

    @Provides
    @ForActivity
    internal fun provideContext(): Context {
        return activity
    }

    @Provides
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    internal fun provideLayoutInflater(): LayoutInflater {
        return activity.layoutInflater
    }
}