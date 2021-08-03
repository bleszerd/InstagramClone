package com.github.bleszerd.instagramclone.common.view

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.utils.Colors
import com.github.bleszerd.instagramclone.common.utils.Drawables

/**
InstagramClone
02/08/2021 - 18:15
Created by bleszerd.
@author alive2k@programmer.net
 */
abstract class AbstractActivity : AppCompatActivity(), View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInject()
    }

    fun findDrawable(@DrawableRes drawableId: Int): Drawable? {
        return Drawables.getDrawable(this, drawableId)
    }

    fun findColor(@ColorRes colorId: Int): Int {
        return Colors.getColor(this, colorId)
    }

    protected open fun onInject() {}

    override fun showProgressBar() {}

    override fun hideProgressBar() {}

    override fun setStatusBarDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = findColor(R.color.black)
        }
    }
}