package com.github.bleszerd.instagramclone.register.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractFragment
import com.github.bleszerd.instagramclone.databinding.FragmentRegisterEmailBinding

/**
InstagramClone
30/07/2021 - 18:58
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterEmailFragment() : AbstractFragment<RegisterPresenter>(), RegisterView.EmailView {
    lateinit var binding: FragmentRegisterEmailBinding

    companion object {
        fun newInstance(presenter: RegisterPresenter): RegisterEmailFragment{
            val fragment = RegisterEmailFragment()

            fragment.presenter = presenter
            presenter.setEmailView(fragment)

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterEmailBinding.inflate(layoutInflater)

        setButtonNextOnClick()

        setLoginTextButtonClick()

        setInputListeners()

        return binding.root
    }

    private fun setLoginTextButtonClick() {
        binding.registerFragmentNamePasswordTextViewLogin.setOnClickListener {
            if (isAdded && activity != null) {
                activity?.finish()
            }
        }
    }

    private fun setButtonNextOnClick() {
        binding.registerFragmentButtonNext.setOnClickListener {
            presenter?.setEmail(binding.registerFragmentEmailEditTextEmail.text.toString())
        }
    }

    private fun setInputListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.registerFragmentButtonNext.isEnabled =
                    !binding.registerFragmentEmailEditTextEmail.text.isNullOrEmpty()

                binding.registerFragmentEmailEditTextEmail.background =
                    findDrawable(R.drawable.edit_text_background)
                binding.registerFragmentEmailInputLayoutEmail.error = null
                binding.registerFragmentEmailInputLayoutEmail.isErrorEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.registerFragmentEmailEditTextEmail.addTextChangedListener(textWatcher)
    }

    override fun onFailureForm(emailError: String) {
        binding.registerFragmentEmailInputLayoutEmail.error =  emailError
        binding.registerFragmentEmailEditTextEmail.background = findDrawable(R.drawable.edit_text_background_error)
    }

    override fun showProgressBar() {
        binding.registerFragmentButtonNext.showProgress(true)
    }

    override fun hideProgressBar() {
        binding.registerFragmentButtonNext.showProgress(false)
    }
}