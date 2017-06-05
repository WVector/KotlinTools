package com.vector.kotlintoolsdemo

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import com.pawegio.kandroid.toast
import com.vector.kotlintoolsdemo.lib.alert

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

    }

    fun dialog1(view: View) {
        alert("", "标题") {
            val view1 = EditText(this@DialogActivity)
            customView(view1)
            positiveButton {

            }
            show()
        }


        val editText = EditText(this)

        AlertDialog.Builder(this)
                .setTitle("标题")
//                .setMessage("内容")
                .setView(editText)
                .setNegativeButton("消积") { dialog, which ->

                }
                .setPositiveButton("积极") { dialog, which ->

                    toast(editText.text.toString().trim())

                }
                .create()
                .show()
    }
}
