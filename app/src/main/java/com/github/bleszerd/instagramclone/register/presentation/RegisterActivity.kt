package com.github.bleszerd.instagramclone.register.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.CustomDialog
import com.github.bleszerd.instagramclone.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val customDialog = CustomDialog(this).apply {
            setTitle(R.string.define_photo_profile)
            addButton({ view ->
                when (view.id) {
                    R.string.take_picture -> {
                        println("Tirar foto")
                    }
                }
            }, R.string.take_picture, R.string.search_gallery)
        }

        customDialog.show()
    }
}