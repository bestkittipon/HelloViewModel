package com.thebest.helloviewmodel.utils

import android.util.Log

class SingletonTemplate{
    val exemple:String = "test"
    val tag = SingletonTemplate::javaClass.name
    private constructor() {
        Log.d(tag , "instance is created")
    }

    companion object {
        val instance: SingletonTemplate by lazy { SingletonTemplate() }
    }

    fun workA() = "AAA"
}