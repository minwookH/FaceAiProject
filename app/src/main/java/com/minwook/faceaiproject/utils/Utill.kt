package com.minwook.faceaiproject.utils

import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Utill {
    companion object {

        @Throws(IOException::class)
        fun createImageFile(): File {
            val timeStamp = SimpleDateFormat("HHmmss").format(Date())
            val imageFileName = "face_${timeStamp}"
            val storageDir = File(Environment.getExternalStorageDirectory().absolutePath + "/faceai/")
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }
            return File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
            )
        }
    }
}