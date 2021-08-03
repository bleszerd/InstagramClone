package com.github.bleszerd.instagramclone.common.models

import android.os.Handler
import android.os.Looper

/**
InstagramClone
03/08/2021 - 10:36
Created by bleszerd.
@author alive2k@programmer.net
 */
object Database {
    private lateinit var onSuccessListener: OnSuccessListener<GenericModel>
    private val usersAuth: MutableSet<UserAuth> = HashSet()
    private var userAuth: UserAuth? = null

    private var onFailureListener: OnFailureListener? = null
    private var onCompleteListener: OnCompleteListener? = null

    init {
//        usersAuth.add(UserAuth("user1@gmail.com", "1234"))
//        usersAuth.add(UserAuth("user2@gmail.com", "4567"))
//        usersAuth.add(UserAuth("user3@gmail.com", "7891"))
//        usersAuth.add(UserAuth("user4@gmail.com", "1112"))
//        usersAuth.add(UserAuth("user5@gmail.com", "1314"))
//        usersAuth.add(UserAuth("user6@gmail.com", "1516"))
    }


    fun addOnSuccessListener(listener: OnSuccessListener<GenericModel>) {
        this.onSuccessListener = listener
    }

    fun addOnFailureListener(listener: OnFailureListener) {
        this.onFailureListener = listener
    }

    fun addOnCompleteListener(listener: OnCompleteListener) {
        this.onCompleteListener = listener
    }

    fun login(email: String, password: String) {
        timeout {
            val userAuth = UserAuth(email, password)

            if (usersAuth.contains(userAuth)) {
                this.userAuth = userAuth
                onSuccessListener.onSuccess(userAuth)
            } else {
                this.userAuth = null
                onFailureListener?.onFailure(IllegalAccessError("Usuário não encontrado"))
            }

            onCompleteListener?.onComplete()
        }
    }

    private fun timeout(r: Runnable) {
        Handler(Looper.getMainLooper()).postDelayed({
            r.run()
        }, 2000)
    }

    interface OnSuccessListener<T: GenericModel> {
        fun onSuccess(response: T)
    }

    interface OnFailureListener {
        fun onFailure(e: Error)
    }

    interface OnCompleteListener {
        fun onComplete()
    }
}