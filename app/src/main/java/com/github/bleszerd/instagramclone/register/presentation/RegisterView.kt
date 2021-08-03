package com.github.bleszerd.instagramclone.register.presentation

import android.content.Context

/**
InstagramClone
03/08/2021 - 14:50
Created by bleszerd.
@author alive2k@programmer.net
 */
interface RegisterView {

    fun showNextView(step: RegisterSteps){}

    interface EmailView {
        fun getContext(): Context

        fun onFailureForm(emailError: String)
    }

    interface NamePasswordView {
        fun getContext(): Context

        fun onFailureForm(nameError: String?, passwordError: String?)
    }
}