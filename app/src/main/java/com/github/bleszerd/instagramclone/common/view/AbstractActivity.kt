package com.github.bleszerd.instagramclone.common.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
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

    protected open fun onInject() {}

    override fun showProgressBar() {}

    override fun hideProgressBar() {}
}