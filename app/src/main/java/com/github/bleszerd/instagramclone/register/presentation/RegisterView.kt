package com.github.bleszerd.instagramclone.register.presentation

import android.content.Context
import android.net.Uri
import com.github.bleszerd.instagramclone.common.view.View

/**
InstagramClone
03/08/2021 - 14:50
Created by bleszerd.
@author alive2k@programmer.net
 */
interface RegisterView {

    fun showNextView(step: RegisterSteps) {}

    fun onUserCreated()

    fun showCamera()

    fun showGallery()

    interface EmailView {
        fun getContext(): Context

        fun onFailureForm(emailError: String)
    }

    interface NamePasswordView : View {
//        fun getContext(): Context

        fun onFailureForm(nameError: String?, passwordError: String?)

        fun onFailureCreateUser(message: String)
    }

    interface WelcomeView {}

    interface PhotoView {
        fun onImageCropped(uri: Uri)
    }
}