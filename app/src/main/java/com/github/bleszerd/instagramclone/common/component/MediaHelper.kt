package com.github.bleszerd.instagramclone.common.component

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
InstagramClone
04/08/2021 - 16:37
Created by bleszerd.
@author alive2k@programmer.net
 */
class MediaHelper {
    companion object {
        private var savedImageUri: Uri? = null
        private lateinit var cropImageView: CropImageView
        private var mCroppedUri: Uri? = null
        private const val CAMERA_CODE = 1
        private const val GALLERY_CODE = 2

        private lateinit var activity: Activity
        private lateinit var fragment: Fragment
        private var listener: OnImageCroppedListener? = null

        fun init(fragment: Fragment) {
            this.fragment = fragment
        }

        fun init(activity: Activity) {
            this.activity = activity
        }

        fun setActivity(activity: Activity) {
            this.activity = activity
        }

        fun listener(listener: OnImageCroppedListener) {
            this.listener = listener
        }

        fun cropView(cropImageView: CropImageView) {
            this.cropImageView = cropImageView
            cropImageView.apply {
                setAspectRatio(1, 1)
                setFixedAspectRatio(true)
                setOnCropImageCompleteListener { view, result ->
                    val uri = result.uri
                    if (uri != null && listener != null) {
                        listener?.onImageCropped(uri)
                        cropImageView.visibility = View.GONE
                    }
                }
            }

        }

        fun setFragment(fragment: Fragment) {
            this.fragment = fragment
        }

        @SuppressLint("UseRequireInsteadOfGet")
        fun getContext(): Context {
            if (::fragment.isInitialized && fragment.activity != null)
                return fragment.context!!

            return activity
        }


        fun chooserCamera() {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (i.resolveActivity(getContext().packageManager) != null) {
                var photoFile: File? = null
                try {
                    photoFile = createImageFile()
                } catch (e: Error) {

                }

                if (photoFile != null) {
                    mCroppedUri = FileProvider.getUriForFile(getContext(),
                        "com.github.bleszerd.instagramclone.fileprovider",
                        photoFile)
                    i.putExtra(MediaStore.EXTRA_OUTPUT, mCroppedUri)
                    activity.startActivityForResult(i, CAMERA_CODE)
                }
            }
        }

        private fun createImageFile(): File {
            val timestamp = SimpleDateFormat("yyyyMMdd HHmmss", Locale.getDefault()).format(Date())
            val imageFileName = "JPEG_${timestamp}_"
            val storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File.createTempFile(imageFileName, ".jpg", storageDir)
        }

        fun chooserGallery() {
            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            activity.startActivityForResult(i, GALLERY_CODE)
        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {
                if (CropImage.isReadExternalStoragePermissionsRequired(getContext(),
                        mCroppedUri!!)
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (::activity.isInitialized) {
                            activity.requestPermissions(arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE),
                                CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE
                            )
                        } else {
                            fragment.requestPermissions(arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE),
                                CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE
                            )
                        }
                    }
                } else {
                    startCropImageActivity()
                }
            } else if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK) {
                this.mCroppedUri = CropImage.getPickImageResultUri(getContext(), data)
                startCropImageActivity()
            }
        }

        private fun startCropImageActivity() {
            cropImageView.setImageUriAsync(mCroppedUri)
        }

        fun cropImage() {
            val getImage = getContext().externalCacheDir
            if (getImage != null) {
                this.savedImageUri =
                    Uri.fromFile(File(getImage.path, "${System.currentTimeMillis()}.jpeg"))
            }
            cropImageView.saveCroppedImageAsync(savedImageUri)
        }

        interface OnImageCroppedListener {
            fun onImageCropped(uri: Uri)
        }
    }
}


