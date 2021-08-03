package com.github.bleszerd.instagramclone.login.presentation

import android.os.Handler
import android.os.Looper
import com.github.bleszerd.instagramclone.login.datasource.LoginDataSource

/**
InstagramClone
03/08/2021 - 09:28
Created by bleszerd.
@author alive2k@programmer.net
 */
class LoginPresenter(private val view: LoginView, private val dataSource: LoginDataSource) {
    fun login(email: String, password: String){
        view.showProgressBar()

        Handler(Looper.getMainLooper()).postDelayed({
            view.hideProgressBar()
            view.onFailureForm("Error1", "Error2")
        }, 2000)
    }
}