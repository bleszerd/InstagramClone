package com.github.bleszerd.instagramclone.main.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.databinding.ActivityMainBinding


class MainActivity : AbstractActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setLightStatusBar()

        configureToolbar()

        setContentView(binding.root)
    }

    private fun configureToolbar() {
        val toolbar = binding.activityMainToolbarToolbar
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            val cameraDrawable =
                AppCompatResources.getDrawable(applicationContext, R.drawable.ic_insta_camera)

            supportActionBar!!.title = ""
            supportActionBar!!.setHomeAsUpIndicator(cameraDrawable)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 and above
            window.statusBarColor = findColor(R.color.gray)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 to 5.0
            val localLayoutParams = window.attributes
            localLayoutParams.flags =
                (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0 can modify the status bar text color and icon later
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun getContext(): Context {
        return applicationContext
    }
}