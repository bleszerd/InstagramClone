package com.github.bleszerd.instagramclone.login.presentation

import com.github.bleszerd.instagramclone.common.view.View

/**
InstagramClone
02/08/2021 - 18:09
Created by bleszerd.
@author alive2k@programmer.net
 */
interface LoginView: View {
    fun onFailureForm(emailError: String?, passwordError: String?)
    fun onUserLogged()
}