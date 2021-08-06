package com.github.bleszerd.instagramclone.login.presentation

import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.models.GenericModel
import com.github.bleszerd.instagramclone.common.models.UserAuth
import com.github.bleszerd.instagramclone.common.presenter.Presenter
import com.github.bleszerd.instagramclone.common.utils.Strings
import com.github.bleszerd.instagramclone.login.datasource.LoginDataSource

/**
InstagramClone
03/08/2021 - 09:28
Created by bleszerd.
@author alive2k@programmer.net
 */
class LoginPresenter(private val view: LoginView, private val dataSource: LoginDataSource): Presenter{
    fun login(email: String, password: String){
        if(!Strings.emailIsValid(email)){
            view.onFailureForm(view.getContext().getString(R.string.invalid_email), null)
            return
        }

        view.showProgressBar()

        dataSource.login(email, password, this)
    }

    override fun onSuccess(response: Any) {
        view.onUserLogged()
    }

    override fun onError(message: String?) {
        view.onFailureForm(null, message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }
}