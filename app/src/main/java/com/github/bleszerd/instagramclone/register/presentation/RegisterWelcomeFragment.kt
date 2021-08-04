package com.github.bleszerd.instagramclone.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractFragment
import com.github.bleszerd.instagramclone.databinding.FragmentRegisterWelcomeBinding

/**
InstagramClone
31/07/2021 - 09:18
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterWelcomeFragment: AbstractFragment<RegisterPresenter>(), RegisterView.WelcomeView {
    lateinit var binding: FragmentRegisterWelcomeBinding

    companion object {
        fun newInstance(presenter: RegisterPresenter): RegisterWelcomeFragment {
            val fragment = RegisterWelcomeFragment()

            fragment.presenter = presenter
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterWelcomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerFragmentPhotoLoadingButtonNext.isEnabled = true
        binding.registerFragmentWelcomeTextViewWelcomeText.text = context.getString(R.string.welcome_to_instagram, presenter?.name)

        binding.registerFragmentPhotoLoadingButtonNext.setOnClickListener {
            presenter?.showPhotoView()
        }
    }
}