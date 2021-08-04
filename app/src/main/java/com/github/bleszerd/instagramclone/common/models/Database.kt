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
    private var userAuth: UserAuth? = null

    private val usersAuth: MutableSet<UserAuth> = HashSet()
    private val users: MutableSet<User> = HashSet()

    private var onFailureListener: OnFailureListener? = null
    private var onCompleteListener: OnCompleteListener? = null

    init {
        usersAuth.add(UserAuth("email@example.com", "example"))
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

    fun createUser(name: String, email: String, password: String){
        timeout {
            val userAuth = UserAuth(email, password)
            val user = User(email, name)

            usersAuth.add(userAuth)

            val added = users.add(user)
            if(added){
                this.userAuth = userAuth
                onSuccessListener.onSuccess(userAuth)
            } else {
                this.userAuth = null
                onFailureListener?.onFailure(Error("Usuário já existe"))
            }

            onCompleteListener?.onComplete()
        }
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