package com.github.bleszerd.instagramclone.common.presenter

import com.github.bleszerd.instagramclone.common.models.UserAuth

/**
InstagramClone
03/08/2021 - 10:16
Created by bleszerd.
@author alive2k@programmer.net
 */
interface Presenter {
    fun onSuccess(response: UserAuth)
    fun onError(message: String?)
    fun onComplete()
}