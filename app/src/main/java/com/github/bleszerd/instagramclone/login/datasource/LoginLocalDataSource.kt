package com.github.bleszerd.instagramclone.login.datasource

import com.github.bleszerd.instagramclone.common.models.Database
import com.github.bleszerd.instagramclone.common.models.GenericModel
import com.github.bleszerd.instagramclone.common.models.UserAuth
import com.github.bleszerd.instagramclone.common.presenter.Presenter

/**
InstagramClone
03/08/2021 - 09:27
Created by bleszerd.
@author alive2k@programmer.net
 */
class LoginLocalDataSource : LoginDataSource {
    override fun login(email: String, password: String, presenter: Presenter) {
        Database.apply {
            login(email, password)
            addOnSuccessListener(object : Database.OnSuccessListener<GenericModel> {
                override fun onSuccess(response: GenericModel) {
                    presenter.onSuccess(response as UserAuth)
                }
            })
            addOnFailureListener(object : Database.OnFailureListener {
                override fun onFailure(e: Error) {
                    presenter.onError(e.message)
                }
            })
            addOnCompleteListener(object : Database.OnCompleteListener {
                override fun onComplete() {
                    presenter.onComplete()
                }
            })
        }
    }
}