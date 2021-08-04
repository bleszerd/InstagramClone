package com.github.bleszerd.instagramclone.register.datasource

import com.github.bleszerd.instagramclone.common.models.Database
import com.github.bleszerd.instagramclone.common.models.GenericModel
import com.github.bleszerd.instagramclone.common.models.UserAuth
import com.github.bleszerd.instagramclone.common.presenter.Presenter

/**
InstagramClone
04/08/2021 - 08:26
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterLocalDataSource : RegisterDataSource {
    override fun createUser(
        name: String,
        email: String,
        password: String,
        presenter: Presenter,
    ) {
        Database.createUser(name, email, password)
        Database.addOnSuccessListener(object : Database.OnSuccessListener<GenericModel> {
            override fun onSuccess(response: GenericModel) {
                presenter.onSuccess(response as UserAuth)
            }
        })
        Database.addOnFailureListener(object : Database.OnFailureListener {
            override fun onFailure(e: Error) {
                presenter.onError(e.message)
            }
        })
        Database.addOnCompleteListener(object : Database.OnCompleteListener {
            override fun onComplete() {
                presenter.onComplete()
            }
        })


    }
}