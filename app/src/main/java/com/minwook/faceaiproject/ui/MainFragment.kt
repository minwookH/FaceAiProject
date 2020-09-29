package com.minwook.faceaiproject.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.minwook.faceaiproject.R
import com.minwook.faceaiproject.utils.Utill
import com.tedpark.tedpermission.rx2.TedRx2Permission
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File
import java.io.IOException

class MainFragment : Fragment() {
    private val RESULT_CAMERA_FINISH = 5000
    private var picturePath: String? = null
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_load_camera.setOnClickListener {
                TedRx2Permission.with(context)
                    .setRationaleTitle("R.string.rationale_title")
                    .setRationaleMessage("R.string.rationale_message") // "we need permission for read contact and find your location"
                    .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .request()
                    .subscribe({
                        if (it.isGranted) {
                            onCamera()
                        } else {
                            //finish()
                        }
                    }, {

                    })
        }

        bt_load_gallary.setOnClickListener {
            TedRx2Permission.with(context)
                .setRationaleTitle("R.string.rationale_title")
                .setRationaleMessage("R.string.rationale_message") // "we need permission for read contact and find your location"
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request()
                .subscribe({
                    if (it.isGranted) {
                        onGallery()
                    } else {
                        //finish()
                    }
                }, {

                })
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

    private fun onGallery() {
        TedImagePicker.with(requireContext())
            .mediaType(MediaType.IMAGE)
            .showCameraTile(false)
            .start {
                addAttachImage(it)
            }
    }

    private fun onCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var photoFile: File? = null
        try {
            photoFile = Utill.createImageFile()

            if (photoFile != null) {
                val photoUri = FileProvider.getUriForFile(requireContext(), requireContext().packageName, photoFile)

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
}