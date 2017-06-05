package com.vector.kotlintoolsdemo

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log

class PhotoFragmentActivity : AppCompatActivity() {
    companion object {
        val TAG: String = PhotoFragmentActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_fragment)
    }

    /**
     * 解决嵌套Fragment调用startactivityOnResult不起作用的问题
     */
    private var mFragmentForResult: Fragment? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Log.d(TAG, "onActivityResult() called with: requestCode = [$requestCode], resultCode = [$resultCode], data = [$data]")
        val index = requestCode shr 16
        if (index != 0) {
            if (mFragmentForResult != null) {
                mFragmentForResult!!.onActivityResult(requestCode and 0xffff, resultCode, data)
            }
        }
    }

    override fun startActivityFromFragment(fragment: Fragment?, intent: Intent?, requestCode: Int, options: Bundle?) {
        Log.d(TAG, "startActivityFromFragment")
        mFragmentForResult = fragment
        super.startActivityFromFragment(fragment, intent, requestCode, options)
    }

//    override fun startActivityFromFragment(fragment: Fragment, intent: Intent, requestCode: Int) {
//
//        super.startActivityFromFragment(fragment, intent, requestCode)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Log.d(TAG, "onActivityResult() called with: requestCode = [$requestCode], resultCode = [$resultCode], data = [$data]")
//    }

}
