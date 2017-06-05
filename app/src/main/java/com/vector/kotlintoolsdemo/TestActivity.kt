package com.vector.kotlintoolsdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class TestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        Log.d(TAG, "onCreate() called with: savedInstanceState = [$savedInstanceState]")
    }

    companion object {

        private val TAG = TestActivity::class.java.simpleName
    }
}
