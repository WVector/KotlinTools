package com.vector.kotlintoolsdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pawegio.kandroid.startActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun activityPhotoPicker(view: View) {
        startActivity<PhotoActivity>()
    }

    fun fragmentPhotoPicker(view: View) {
        startActivity<PhotoFragmentActivity>()
    }

    fun dialog(view: View) {
        startActivity<DialogActivity>()
    }
}
