package com.github.bleszerd.instagramclone.register.datasource

import android.net.Uri
import com.github.bleszerd.instagramclone.common.models.GenericModel
import com.github.bleszerd.instagramclone.common.presenter.Presenter

/**
InstagramClone
04/08/2021 - 08:25
Created by bleszerd.
@author alive2k@programmer.net
 */
interface RegisterDataSource {
    fun createUser(name: String, email: String, password: String, presenter: Presenter)

    fun addPhoto(uri: Uri, presenter: Presenter)
}