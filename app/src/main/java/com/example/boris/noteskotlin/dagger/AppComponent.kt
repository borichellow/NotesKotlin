package com.example.boris.noteskotlin.dagger

import android.content.res.Resources
import com.appunite.rx.dagger.NetworkScheduler
import com.appunite.rx.dagger.UiScheduler
import com.example.boris.noteskotlin.MainApplication
import com.google.gson.Gson
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    /**
     * There should be provided all objects used in presenters,
     * those providers come from AppModule.java
     */

    @UiScheduler
    fun provideUiScheduler(): rx.Scheduler

    @NetworkScheduler
    fun provideNetworkScheduler(): rx.Scheduler

    fun provideResources(): Resources

    fun provideGson(): Gson

    /**
     * Inject methods for Activities that dont need any special parameters to pass
     */

    fun inject(mainApplication: MainApplication)
}