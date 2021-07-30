package com.github.bleszerd.instagramclone.login.presentation

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.loginActivityButtonEnter.isEnabled = !s.isNullOrEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Populate references
        val editLayoutEmail = binding.loginActivityInputLayoutEmail
        val editLayoutPassword = binding.loginActivityInputLayoutPassword
        val editTextEmail = binding.loginActivityEditTextEmail
        val editTextPassword = binding.loginActivityEditTextPassword
        val buttonEnter = binding.loginActivityButtonEnter

        //Force errors on button click
        buttonEnter.setOnClickListener {
            buttonEnter.showProgress(true)

            Handler(mainLooper).postDelayed({
                editLayoutEmail.error = "Este e-mail é inválido!"
                editTextEmail.background =
                    ContextCompat.getDrawable(this, R.drawable.edit_text_background_error)

                editLayoutPassword.error = "Senha inválida!"
                editTextPassword.background =
                    ContextCompat.getDrawable(this, R.drawable.edit_text_background_error)

                buttonEnter.showProgress(false)
            }, 2000)
        }

        //Set input listeners
        editTextEmail.addTextChangedListener(textWatcher)
    }
}