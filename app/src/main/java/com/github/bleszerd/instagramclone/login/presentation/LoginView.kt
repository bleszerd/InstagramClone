package com.github.bleszerd.instagramclone.login.presentation

/**
InstagramClone
02/08/2021 - 18:09
Created by bleszerd.
@author alive2k@programmer.net
 */
interface LoginView {
    fun onFailureForm(emailError: String?, passwordError: String?)
    fun onUserLogged()
}