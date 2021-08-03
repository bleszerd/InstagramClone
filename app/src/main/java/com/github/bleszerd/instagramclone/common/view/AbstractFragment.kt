package com.github.bleszerd.instagramclone.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.github.bleszerd.instagramclone.common.utils.Colors
import com.github.bleszerd.instagramclone.common.utils.Drawables

/**
InstagramClone
03/08/2021 - 14:56
Created by bleszerd.
@author alive2k@programmer.net
 */
abstract class AbstractFragment: Fragment(), View {
    override fun getContext(): Context {
        return super.requireContext()
    }

    override fun showProgressBar() {
        super.showProgressBar()
    }

    override fun hideProgressBar() {
        super.hideProgressBar()
    }

    override fun setStatusBarDark() {
        super.setStatusBarDark()
    }

    fun findDrawable(@DrawableRes drawableId: Int): Drawable? {
        return Drawables.getDrawable(context, drawableId)
    }

    fun findColor(@ColorRes colorId: Int): Int {
        return Colors.getColor(context, colorId)
    }
}