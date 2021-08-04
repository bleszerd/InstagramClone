package com.github.bleszerd.instagramclone.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.common.view.AbstractFragment
import com.github.bleszerd.instagramclone.databinding.FragmentRegisterNamePasswordBinding
import com.github.bleszerd.instagramclone.databinding.FragmentRegisterPhotoBinding

/**
InstagramClone
31/07/2021 - 09:18
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterPhotoFragment: AbstractFragment<RegisterPresenter>(), RegisterView.PhotoView {
    lateinit var binding: FragmentRegisterPhotoBinding

    companion object {
        fun newInstance(presenter: RegisterPresenter): RegisterPhotoFragment {
            val fragment = RegisterPhotoFragment()
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
            // TODO: 04/08/2021
        }

        binding.registerFragmentPhotoButtonJumpPhoto.setOnClickListener {
            presenter?.jumpRegistration()
        }
    }
}