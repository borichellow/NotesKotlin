package com.example.boris.noteskotlin.base

import android.os.Bundle
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
    }

    abstract fun initDagger()

}