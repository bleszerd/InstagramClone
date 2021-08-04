package com.github.bleszerd.instagramclone.common.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

/**
InstagramClone
04/08/2021 - 16:37
Created by bleszerd.
@author alive2k@programmer.net
 */
class MediaHelper {
    companion object {
        private lateinit var cropImageView: CropImageView
        private var mCroppedUri: Uri? = null
        private const val CAMERA_CODE = 1
        private const val GALLERY_CODE = 2

        private lateinit var activity: Activity
        private lateinit var fragment: Fragment

        fun setActivity(activity: Activity) {
            this.activity = activity
        }

        fun cropView(cropImageView: CropImageView){
            this.cropImageView = cropImageView
        }

        fun setFragment(fragment: Fragment) {
            this.fragment = fragment
        }

        fun getContext(): Context {
            if(fragment != null && fragment.activity != null)
                return fragment.requireContext()

            return activity
        }

        fun chooserGallery(){
            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            activity.startActivityForResult(i, GALLERY_CODE)
        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
            if(requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK){

            } else if(requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK) {
                this.mCroppedUri = CropImage.getPickImageResultUri(getContext(), data)
                startCropImageActivity()
            }
        }

        private fun startCropImageActivity() {
            cropImageView.setImageUriAsync(mCroppedUri)
        }
    }
}


