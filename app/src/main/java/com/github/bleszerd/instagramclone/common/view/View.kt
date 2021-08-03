package com.github.bleszerd.instagramclone.common.view

import android.content.Context

/**
InstagramClone
03/08/2021 - 09:24
Created by bleszerd.
@author alive2k@programmer.net
 */
interface View {
    fun getContext(): Context
    fun showProgressBar(){}
    fun hideProgressBar(){}
    fun setStatusBarDark(){}
}