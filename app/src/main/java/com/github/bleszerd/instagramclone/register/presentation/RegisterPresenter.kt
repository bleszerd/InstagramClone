package com.github.bleszerd.instagramclone.register.presentation

import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.utils.Strings

/**
InstagramClone
03/08/2021 - 15:18
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterPresenter {
    private lateinit var email: String
    private lateinit var emailView: RegisterView.EmailView

    fun setEmailView(emailView: RegisterView.EmailView) {
        this.emailView = emailView
    }

    fun setEmail(email: String){
        if(!Strings.emailIsValid(email)){
            emailView.onFailureForm(emailView.getContext().getString(R.string.invalid_email))
            return
        }

        this.email = email
    }
}