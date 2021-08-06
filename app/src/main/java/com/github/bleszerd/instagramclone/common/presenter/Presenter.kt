package com.github.bleszerd.instagramclone.common.presenter

import com.github.bleszerd.instagramclone.common.models.GenericModel
import com.github.bleszerd.instagramclone.common.models.UserAuth

/**
InstagramClone
03/08/2021 - 10:16
Created by bleszerd.
@author alive2k@programmer.net
 */
interface Presenter {
    fun onSuccess(response: Any)
    fun onError(message: String?)
    fun onComplete()
}