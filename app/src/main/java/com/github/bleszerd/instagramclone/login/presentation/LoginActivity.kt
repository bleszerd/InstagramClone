package com.github.bleszerd.instagramclone.login.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editLayoutEmail = binding.loginActivityInputLayoutEmail
        editLayoutEmail.error = "Este e-mail é inválido!"

        val editTextEmail = binding.loginActivityEditTextEmail
        editTextEmail.background = ContextCompat.getDrawable(this, R.drawable.edit_text_background_error)
    }
}