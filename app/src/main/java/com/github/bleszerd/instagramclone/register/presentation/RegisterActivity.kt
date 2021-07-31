package com.github.bleszerd.instagramclone.register.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.CustomDialog

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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