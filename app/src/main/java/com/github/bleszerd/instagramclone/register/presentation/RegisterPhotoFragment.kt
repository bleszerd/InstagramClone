package com.github.bleszerd.instagramclone.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.FragmentRegisterNamePasswordBinding

/**
InstagramClone
31/07/2021 - 09:18
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterPhotoFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_register_photo, container, false)
        // TODO: 31/07/2021 Scroll gravity top
    }
}