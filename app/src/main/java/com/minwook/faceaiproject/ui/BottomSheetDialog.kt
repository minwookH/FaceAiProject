package com.minwook.faceaiproject.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.minwook.faceaiproject.R
import com.minwook.faceaiproject.utils.Utill
import kotlinx.android.synthetic.main.dialog_bottom_sheet.*
import java.io.File
import java.io.IOException

class BottomSheetDialog(private val activity: AppCompatActivity): BottomSheetDialogFragment() {

    private val RESULT_CAMERA_FINISH = 5000
    private var picturePath: String? = null
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_dialog_menu_one.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            var photoFile: File? = null
            try {
                photoFile = Utill.createImageFile()

                if (photoFile != null) {
                    val photoUri = FileProvider.getUriForFile(requireContext(), requireContext().packageName, photoFile)

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    picturePath = photoFile.absolutePath
                    activity.startActivityForResult(intent, RESULT_CAMERA_FINISH)
                }
            } catch (e: IOException) {
                //CLog.saveLog(e.localizedMessage)
            } catch (e: Exception) {
                //CLog.saveLog(e.localizedMessage)
            }
        }

        tv_dialog_menu_two.setOnClickListener {

        }
    }
}