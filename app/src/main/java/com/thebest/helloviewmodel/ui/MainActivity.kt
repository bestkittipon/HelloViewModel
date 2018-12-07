package com.thebest.helloviewmodel.ui


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.thebest.helloviewmodel.R

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content_container, PostFragment.newInstance())
                addToBackStack(null)
            }.commit()
        }
    }
}
