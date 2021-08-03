package com.github.bleszerd.instagramclone.login.presentation

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

class LoginActivity : AbstractActivity(), LoginView, TextWatcher {
    private lateinit var binding: ActivityLoginBinding

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Tint status bar
        paintStatusBar()

        //Set button click action
        setButtonEnterClickListener(binding.loginActivityButtonEnter)

        //Set input listeners
        binding.loginActivityEditTextEmail.addTextChangedListener(this)
        binding.loginActivityEditTextPassword.addTextChangedListener(this)
    }

    override fun onInject() {
        val dataSource = LoginLocalDataSource()
        presenter = LoginPresenter(this, dataSource)
    }

    private fun paintStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
    }

    override fun showProgressBar() {
        binding.loginActivityButtonEnter.showProgress(true)
    }

    override fun hideProgressBar() {
        binding.loginActivityButtonEnter.showProgress(false)
    }

    private fun setButtonEnterClickListener(buttonEnter: LoadingButton) {
        buttonEnter.setOnClickListener {
            presenter.login(
                binding.loginActivityEditTextEmail.text.toString(),
                binding.loginActivityEditTextPassword.text.toString()
            )
        }
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


    /* === Input listener implementation === */
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//        binding.loginActivityButtonEnter.isEnabled = !s.isNullOrEmpty()
        binding.loginActivityButtonEnter.isEnabled =
            (!binding.loginActivityEditTextEmail.text.isNullOrEmpty() && !binding.loginActivityEditTextPassword.text.isNullOrEmpty())
    }

    override fun afterTextChanged(s: Editable?) {}

}