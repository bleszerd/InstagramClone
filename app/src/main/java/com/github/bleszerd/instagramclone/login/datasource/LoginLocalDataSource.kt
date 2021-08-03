package com.github.bleszerd.instagramclone.login.datasource

import com.github.bleszerd.instagramclone.common.presenter.Presenter

/**
InstagramClone
03/08/2021 - 09:27
Created by bleszerd.
@author alive2k@programmer.net
 */
class LoginLocalDataSource: LoginDataSource {
    override fun login(email: String, password: String, presenter: Presenter) {
        super.login(email, password, presenter)
        presenter.onError("Error1")
        presenter.onComplete()
    }
}