package com.songlan.cameraalbumtest

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val takePhoto = 1
    private lateinit var outputImage: File
    private lateinit var imgUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.photoBtn -> {
                outputImage = File(externalCacheDir, "output_img.jpg")
                if (outputImage.exists()) {
                    outputImage.delete()
                }
                outputImage.createNewFile()

                imgUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    FileProvider.getUriForFile(
                        this,
                        "com.songlan.cameraalbumtest.fileprovider",
                        outputImage
                    )
                } else {
                    Uri.fromFile(outputImage)
                }

                val intent = Intent("android.media.action.IMAGE_CAPTURE")
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri)
                startActivityForResult(intent, takePhoto)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            takePhoto -> {
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imgUri))
                    imageView.setImageBitmap(rotateIfRequired(bitmap))
                } else {
                    Toast.makeText(this, "你放弃了拍摄照片", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotatedBitmap
    }


}