package com.vector.kotlintoolsdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pawegio.kandroid.d
import com.pawegio.kandroid.textWatcher
import com.vector.kotlintoolsdemo.lib.selector
import kotlinx.android.synthetic.main.activity_photo.*
import java.io.File


class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        et.textWatcher {
            et.textWatcher {
                beforeTextChanged { charSequence, i, j, k ->
                    val msg = "before : " + charSequence.toString()

                    d { msg }
                    d(msg)
                    println(msg)

                    if (msg.length > 5) {

                    }
                }

                afterTextChanged {
                    val msg = "after : " + it.toString()

                    d { msg }
                    d(msg)
                    println(msg)

                }

                onTextChanged { charSequence, i, j, l ->
                    val msg = "change : " + charSequence.toString()

                    d { msg }
                    d(msg)
                    println(msg)
                }

            }


        }
    }

    private val srcPath: String = Environment.getExternalStorageDirectory().absolutePath + File.separator + "iconSrc.jpg"
    private val savePath: String = Environment.getExternalStorageDirectory().absolutePath + File.separator + "iconCroped.jpg"

    fun test(view: View) {

        File(srcPath).createNewFile()
        File(savePath).createNewFile()

        selector("", listOf("拍照", "相册")) {
            when (it) {
                0 -> {
                    PhotoUtils.openCamera(this@PhotoActivity, srcPath, 1)
                }
                else -> {
                    PhotoUtils.openGallery(this@PhotoActivity, 2)
//                    PhotoUtils.openGalleryAndCutFree(this@MainActivity,  Uri.fromFile(File(savePath)), 3)

                }
            }
        }

        grantUriPermission(packageName, PhotoUtils.fromFilePath(savePath, this), Intent.FLAG_GRANT_WRITE_URI_PERMISSION)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println(requestCode)
        println("-----------------------")
        println(data)
        if (1 == requestCode) {
//            PhotoUtils.cutPhoto(this@MainActivity, PhotoUtils.fromFilePath(srcPath, this), PhotoUtils.fromFilePath(savePath, this), true, 1000, 1000, 3)
            PhotoUtils.cutPhoto(this@PhotoActivity, PhotoUtils.fromFilePath(srcPath, this), Uri.fromFile(File(savePath)), true, 1000, 1000, 3)
        } else if (2 == requestCode) {
            if (data != null) {
                println(data.data)
                PhotoUtils.cutPhoto(this@PhotoActivity, data.data, Uri.fromFile(File(savePath)), true, 1000, 1000, 3)
//                PhotoUtils.cutPhoto(this@MainActivity, data.data, PhotoUtils.fromFilePath(savePath, this), true, 200, 200, 3)
            }
        } else if (3 == requestCode) {
//            iv_test.setImageURI(Uri.fromFile(File(savePath)))

            iv_test.setImageBitmap(PhotoUtils.obtainBitmapFromFile(savePath))
        }
    }
}
