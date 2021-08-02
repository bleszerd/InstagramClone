package com.github.bleszerd.instagramclone.common.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
InstagramClone
02/08/2021 - 18:18
Created by bleszerd.
@author alive2k@programmer.net
 */
sealed class Drawables {
    companion object {
        fun getDrawable(context: Context, @DrawableRes drawableId: Int): Drawable? {
            return ContextCompat.getDrawable(context, drawableId)
        }
    }
}