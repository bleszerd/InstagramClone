package com.github.bleszerd.instagramclone.main.presentation

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        window.decorView.windowInsetsController?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray)

        val toolbar = binding.activityMainToolbarToolbar
        setSupportActionBar(toolbar)

        if(supportActionBar != null){
            val cameraDrawable = getDrawable(R.drawable.ic_insta_camera)

            supportActionBar!!.title = ""
            supportActionBar!!.setHomeAsUpIndicator(cameraDrawable)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        setContentView(binding.root)
    }
}