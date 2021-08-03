package com.github.bleszerd.instagramclone.login.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.common.view.LoadingButton
import com.github.bleszerd.instagramclone.databinding.ActivityLoginBinding
import com.github.bleszerd.instagramclone.login.datasource.LoginLocalDataSource

class LoginActivity : AbstractActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Tint status bar
        paintStatusBar()

        //Set button click action
        setButtonEnterClickListener(binding.loginActivityButtonEnter)

        //Set input listeners
        setInputListeners()
    }

    private fun setInputListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Populate references
                val editTextEmail = binding.loginActivityEditTextEmail
                val editTextPassword = binding.loginActivityEditTextPassword

                val inputLayoutEmail = binding.loginActivityInputLayoutEmail
                val inputLayoutPassword = binding.loginActivityInputLayoutPassword

                //Button enabled condition
                binding.loginActivityButtonEnter.isEnabled =
                    (!editTextEmail.text.isNullOrEmpty() && !editTextPassword.text.isNullOrEmpty())

                //Remove error if text has changed
                editTextEmail.background = findDrawable(R.drawable.edit_text_background)
                inputLayoutEmail.error = null
                inputLayoutEmail.isErrorEnabled = false

                editTextPassword.background = findDrawable(R.drawable.edit_text_background)
                inputLayoutPassword.error = null
                inputLayoutPassword.isErrorEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.loginActivityEditTextEmail.addTextChangedListener(textWatcher)
        binding.loginActivityEditTextPassword.addTextChangedListener(textWatcher)
    }

    private fun setButtonEnterClickListener(buttonEnter: LoadingButton) {
        buttonEnter.setOnClickListener {
            presenter.login(
                binding.loginActivityEditTextEmail.text.toString(),
                binding.loginActivityEditTextPassword.text.toString()
            )
        }
    }

    private fun paintStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
    }

    override fun onInject() {
        val dataSource = LoginLocalDataSource()
        presenter = LoginPresenter(this, dataSource)
    }

    override fun showProgressBar() {
        binding.loginActivityButtonEnter.showProgress(true)
    }

    override fun hideProgressBar() {
        binding.loginActivityButtonEnter.showProgress(false)
    }

    override fun onFailureForm(emailError: String?, passwordError: String?) {
        if (emailError != null) {
            binding.loginActivityInputLayoutEmail.error = emailError
            binding.loginActivityEditTextEmail.background =
                findDrawable(R.drawable.edit_text_background_error)
        }


        if (passwordError != null) {
            binding.loginActivityInputLayoutPassword.error = passwordError
            binding.loginActivityEditTextPassword.background =
                findDrawable(R.drawable.edit_text_background_error)
        }
    }

    override fun onUserLogged() {
        // TODO: 02/08/2021
        //Show main activity
    }

    override fun getContext(): Context {
        return applicationContext
    }
}