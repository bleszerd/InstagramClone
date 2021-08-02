package com.github.bleszerd.instagramclone.main.camera.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.FragmentMainGalleryBinding

/**
InstagramClone
30/07/2021 - 18:58
Created by bleszerd.
@author alive2k@programmer.net
 */
class CameraFragment() : Fragment() {
    lateinit var binding: FragmentMainGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // TODO: 02/08/2021 app:layout_scrollFlags="scroll" at toolbar
        val binding = FragmentMainGalleryBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}