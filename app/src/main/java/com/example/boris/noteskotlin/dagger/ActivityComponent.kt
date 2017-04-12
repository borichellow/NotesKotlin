package com.example.boris.noteskotlin.dagger

import com.example.boris.noteskotlin.main.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}