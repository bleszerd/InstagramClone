package com.github.bleszerd.instagramclone.register.presentation

/**
InstagramClone
03/08/2021 - 14:50
Created by bleszerd.
@author alive2k@programmer.net
 */
interface RegisterView {
    interface EmailView {
        fun onFailureForm(emailError: String)

    }
}