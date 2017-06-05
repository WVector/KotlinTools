package com.vector.kotlintoolsdemo


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vector.kotlintoolsdemo.lib.selector
import kotlinx.android.synthetic.main.fragment_photo.*
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class PhotoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_picker.setOnClickListener {
            picker()
        }
    }

    private val srcPath: String = Environment.getExternalStorageDirectory().absolutePath + File.separator + "iconSrc.jpg"
    private val savePath: String = Environment.getExternalStorageDirectory().absolutePath + File.separator + "iconCroped.jpg"
    fun picker() {
        File(srcPath).createNewFile()
        File(savePath).createNewFile()

        selector("", listOf("拍照", "相册")) {
            when (it) {
                0 -> {
                    PhotoUtils.openCamera(this, srcPath, 1)
                }
                else -> {
                    PhotoUtils.openGallery(this, 2)
//                    PhotoUtils.openGalleryAndCutFree(this@MainActivity,  Uri.fromFile(File(savePath)), 3)
                }
            }
        }

//        startActivityForResult()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println(requestCode)
        println("-----------------------")
        println(data)
        if (1 == requestCode) {
//            PhotoUtils.cutPhoto(this@MainActivity, PhotoUtils.fromFilePath(srcPath, this), PhotoUtils.fromFilePath(savePath, this), true, 1000, 1000, 3)
            PhotoUtils.cutPhoto(this, PhotoUtils.fromFilePath(srcPath, activity), Uri.fromFile(File(savePath)), true, 1000, 1000, 3)
        } else if (2 == requestCode) {
            if (data != null) {
                println(data.data)
                PhotoUtils.cutPhoto(this, data.data, Uri.fromFile(File(savePath)), true, 1000, 1000, 3)
//                PhotoUtils.cutPhoto(this@MainActivity, data.data, PhotoUtils.fromFilePath(savePath, this), true, 200, 200, 3)
            }
        } else if (3 == requestCode) {
//            iv_test.setImageURI(Uri.fromFile(File(savePath)))

            iv_test.setImageBitmap(PhotoUtils.obtainBitmapFromFile(savePath))
        }
    }


}
