package com.github.bleszerd.instagramclone.register.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.component.CustomDialog
import com.github.bleszerd.instagramclone.common.view.AbstractFragment
import com.github.bleszerd.instagramclone.databinding.FragmentRegisterPhotoBinding

/**
InstagramClone
31/07/2021 - 09:18
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterPhotoFragment : AbstractFragment<RegisterPresenter>(), RegisterView.PhotoView {
    lateinit var binding: FragmentRegisterPhotoBinding

    companion object {
        fun newInstance(presenter: RegisterPresenter): RegisterPhotoFragment {
            val fragment = RegisterPhotoFragment()
            presenter.setPhotoView(fragment)
            fragment.presenter = presenter
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterPhotoBinding.inflate(layoutInflater)

        // TODO: 31/07/2021 Scroll gravity top
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerFragmentPhotoLoadingButtonNext.isEnabled = true

        binding.registerFragmentPhotoLoadingButtonNext.setOnClickListener {
            val customDialog = CustomDialog(context).apply {
                setTitle(R.string.define_photo_profile)
                addButton(
                    { v ->
                        when (v.id) {
                            R.string.take_picture -> {
                                presenter?.showCamera()
                            }
                            R.string.search_gallery -> {
                                presenter?.showGallery()
                            }
                        }
                    }, R.string.take_picture, R.string.search_gallery
                )
            }

            customDialog.show()
        }

        binding.registerFragmentPhotoButtonJumpPhoto.setOnClickListener {
            presenter?.jumpRegistration()
        }
    }

    override fun onImageCropped(uri: Uri) {
        if(context.contentResolver != null){
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                binding.registerFragmentPhotoCircleImageViewUserPhoto.setImageBitmap(bitmap)
            } catch (e: Error){
                println(e.message)
            }
        }
    }
}