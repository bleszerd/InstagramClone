package com.github.bleszerd.instagramclone.common.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
InstagramClone
03/08/2021 - 14:35
Created by bleszerd.
@author alive2k@programmer.net
 */
sealed class Colors {
    companion object {
        fun getColor(context: Context, @ColorRes colorId: Int): Int {
            return ContextCompat.getColor(context, colorId)
        }
    }
}