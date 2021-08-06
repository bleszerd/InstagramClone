package com.github.bleszerd.instagramclone.login.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.component.LoadingButton
import com.github.bleszerd.instagramclone.common.models.Database
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.databinding.ActivityLoginBinding
import com.github.bleszerd.instagramclone.login.datasource.LoginLocalDataSource
import com.github.bleszerd.instagramclone.main.presentation.MainActivity
import com.github.bleszerd.instagramclone.register.presentation.RegisterActivity

class LoginActivity : AbstractActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarDark()

        val user = Database.userAuth
        if (user != null)
            onUserLogged()

        //Set button click action
        setButtonEnterClickListener(binding.loginActivityButtonEnter)

        binding.loginActivityTextViewRegister.setOnClickListener {
            RegisterActivity.launch(this)
        }

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
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        MainActivity.launch(this, MainActivity.LOGIN_ACTIVITY)
    }

    override fun getContext(): Context {
        return applicationContext
    }
}