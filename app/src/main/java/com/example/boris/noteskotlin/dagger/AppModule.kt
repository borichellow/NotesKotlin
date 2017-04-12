package com.example.boris.noteskotlin.dagger

import android.content.Context
import android.content.res.Resources
import com.appunite.rx.android.MyAndroidSchedulers
import com.appunite.rx.dagger.NetworkScheduler
import com.appunite.rx.dagger.UiScheduler
import com.example.boris.noteskotlin.MainApplication
import com.example.boris.noteskotlin.UserPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import rx.schedulers.Schedulers
import javax.inject.Singleton

@Module class AppModule (val app : MainApplication) {

    @Provides
    @Singleton
    @ForApplication
    fun applicationContext(): Context {
        return app.applicationContext
    }

    @Provides
    internal fun provideResources(@ForApplication context: Context): Resources {
        return context.resources
    }


    @Provides
    @Singleton
    fun userPreferences(@ForApplication context: Context, gson: Gson): UserPreferences {
        return UserPreferences(context, gson)
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @UiScheduler
    @Provides
    @Singleton
    internal fun provideUiScheduler(): rx.Scheduler {
        return MyAndroidSchedulers.mainThread()
    }

    @NetworkScheduler
    @Provides
    @Singleton
    internal fun provideNetworkScheduler(): rx.Scheduler {
        return Schedulers.io()
    }
}