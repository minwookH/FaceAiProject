package com.minwook.faceaiproject.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.minwook.faceaiproject.R
import com.minwook.faceaiproject.utils.Utill
import com.tedpark.tedpermission.rx2.TedRx2Permission
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val RESULT_CAMERA_FINISH = 5000
    private var picturePath: String? = null
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun onCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var photoFile: File? = null
        try {
            photoFile = Utill.createImageFile()

            if (photoFile != null) {
                val photoUri = FileProvider.getUriForFile(this, applicationContext.packageName, photoFile)

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                picturePath = photoFile.absolutePath
                startActivityForResult(intent, RESULT_CAMERA_FINISH)
            }
        } catch (e: IOException) {
            //CLog.saveLog(e.localizedMessage)
        } catch (e: Exception) {
            //CLog.saveLog(e.localizedMessage)
        }
    }

    private fun onGallery() {
        TedImagePicker.with(this)
            .mediaType(MediaType.IMAGE)
            .showCameraTile(false)
            .start {
                addAttachImage(it)
            }
    }

    private fun addAttachImage(image: Uri) {
        imageUri = image

        /*val requestOptions = RequestOptions()
        requestOptions.centerCrop()
        requestOptions.optionalCenterCrop().transform(RoundedCorners(Utils.dpToPx(4f)))
        Glide.with(this)
            .load(imageUri)
            //.transform(CenterCrop(), RoundedCornersTransformation(Utils.dpToPx(4f), 0, RoundedCornersTransformation.CornerType.ALL))
            .into(iv_ocr_image)

        cl_ocr_icon_layout.gone()
        iv_ocr_image.visible()
        tv_ocr_picture_next_button.isEnabled = true*/
    }

    @SuppressLint("CheckResult")
    private fun onAttachFile() {
        TedRx2Permission.with(this)
            .setRationaleTitle("R.string.rationale_title")
            .setRationaleMessage("R.string.rationale_message") // "we need permission for read contact and find your location"
            .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .request()
            .subscribe({
                if (it.isGranted) {
                    //showAttachPopup()
                } else {
                    //finish()
                }
            }, {

            })
    }

    private fun takePicture() {
        onCamera()
    }
}