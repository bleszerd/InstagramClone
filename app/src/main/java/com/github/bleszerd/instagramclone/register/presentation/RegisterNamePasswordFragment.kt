package com.github.bleszerd.instagramclone.register.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractFragment
import com.github.bleszerd.instagramclone.databinding.FragmentRegisterNamePasswordBinding

/**
InstagramClone
31/07/2021 - 09:18
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterNamePasswordFragment : AbstractFragment<RegisterPresenter>(),
    RegisterView.NamePasswordView {
    lateinit var binding: FragmentRegisterNamePasswordBinding

    companion object {
        fun newInstance(presenter: RegisterPresenter): RegisterNamePasswordFragment {
            val fragment = RegisterNamePasswordFragment()

            fragment.presenter = presenter
            presenter.setNamePasswordView(fragment)

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterNamePasswordBinding.inflate(layoutInflater)

        setLoginButtonClick()

        setButtonNextClick()

        addInputTextWatchers()

        return binding.root
    }

    private fun addInputTextWatchers() {
        binding.registerFragmentNameEditTextName.addTextChangedListener(textWatcher)
        binding.registerFragmentNameEditTextPassword.addTextChangedListener(textWatcher)
        binding.registerFragmentNameEditTextPasswordConfirm.addTextChangedListener(textWatcher)
    }

    private fun setLoginButtonClick() {
        binding.registerFragmentNamePasswordTextViewLogin.setOnClickListener {
            if (isAdded && activity != null)
                activity?.finish()
        }
    }

    private fun setButtonNextClick() {
        binding.registerFragmentButtonNext.setOnClickListener {
            presenter?.setNameAndPassword(
                binding.registerFragmentNameEditTextName.text.toString(),
                binding.registerFragmentNameEditTextPassword.text.toString(),
                binding.registerFragmentNameEditTextPasswordConfirm.text.toString()
            )
        }
    }

    override fun onFailureCreateUser(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onFailureForm(nameError: String?, passwordError: String?) {
        if (nameError != null) {
            binding.registerFragmentNameInputLayoutName.error = nameError
            binding.registerFragmentNameEditTextName.background =
                findDrawable(R.drawable.edit_text_background_error)
        }

        if (passwordError != null) {
            binding.registerFragmentNameInputLayoutPassword.error = passwordError
            binding.registerFragmentNameEditTextPassword.background =
                findDrawable(R.drawable.edit_text_background_error)

            binding.registerFragmentNameInputLayoutPasswordConfirm.error = passwordError
            binding.registerFragmentNameEditTextPasswordConfirm.background =
                findDrawable(R.drawable.edit_text_background_error)
        }
    }

    override fun showProgressBar() {
        binding.registerFragmentButtonNext.showProgress(true)
    }

    override fun hideProgressBar() {
        binding.registerFragmentButtonNext.showProgress(false)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.registerFragmentButtonNext.isEnabled =
                !binding.registerFragmentNameEditTextName.text.isNullOrEmpty()
                        && !binding.registerFragmentNameEditTextPassword.text.isNullOrEmpty()
                        && !binding.registerFragmentNameEditTextPasswordConfirm.text.isNullOrEmpty()

            binding.registerFragmentNameEditTextName.background =
                findDrawable(R.drawable.edit_text_background)
            binding.registerFragmentNameInputLayoutName.error = null
            binding.registerFragmentNameInputLayoutName.isErrorEnabled = false

            binding.registerFragmentNameEditTextPassword.background =
                findDrawable(R.drawable.edit_text_background)
            binding.registerFragmentNameInputLayoutPassword.error = null
            binding.registerFragmentNameInputLayoutPassword.isErrorEnabled = false

            binding.registerFragmentNameEditTextPasswordConfirm.background =
                findDrawable(R.drawable.edit_text_background)
            binding.registerFragmentNameInputLayoutPasswordConfirm.error = null
            binding.registerFragmentNameInputLayoutPasswordConfirm.isErrorEnabled = false
        }

        override fun afterTextChanged(s: Editable?) {}
    }
}