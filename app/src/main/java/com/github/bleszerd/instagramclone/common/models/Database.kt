package com.github.bleszerd.instagramclone.common.models

import android.net.Uri
import android.os.Handler
import android.os.Looper

/**
InstagramClone
03/08/2021 - 10:36
Created by bleszerd.
@author alive2k@programmer.net
 */
object Database {
    var userAuth: UserAuth? = null

    private val usersAuth: MutableSet<UserAuth> = HashSet()
    private val users: MutableSet<User> = HashSet()
    private val storages: MutableSet<Uri> = HashSet()

    private var onFailureListener: OnFailureListener? = null
    private var onCompleteListener: OnCompleteListener? = null
    private var onSuccessListener: OnSuccessListener? = null

    init {
        usersAuth.add(UserAuth("email@example.com", "example"))
//        usersAuth.add(UserAuth("user2@gmail.com", "4567"))
//        usersAuth.add(UserAuth("user3@gmail.com", "7891"))
//        usersAuth.add(UserAuth("user4@gmail.com", "1112"))
//        usersAuth.add(UserAuth("user5@gmail.com", "1314"))
//        usersAuth.add(UserAuth("user6@gmail.com", "1516"))

        init()
    }

    fun init() {
        val email = "user1@gmail.com"
        val password = "123"
        val name = "user1"
        val userAuth = UserAuth(email, password)
        val user = User(email, name, userAuth.getUUID())

        usersAuth.add(userAuth)
        users.add(user)
        this.userAuth = userAuth
    }

    fun addOnSuccessListener(listener: OnSuccessListener) {
        this.onSuccessListener = listener
    }

    fun addOnFailureListener(listener: OnFailureListener) {
        this.onFailureListener = listener
    }

    fun addOnCompleteListener(listener: OnCompleteListener) {
        this.onCompleteListener = listener
    }

    fun addPhoto(uuid: String, uri: Uri) {
        timeout {
            val users = Database.users
            users.forEach { user ->
                if (user.uuid == uuid) {
                    user.uri = uri
                }
            }

            storages.add(uri)
            onSuccessListener?.onSuccess(true)
        }
    }

    fun createUser(name: String, email: String, password: String) {
        timeout {
            val userAuth = UserAuth(email, password)
            val user = User(email, name, userAuth.getUUID())

            usersAuth.add(userAuth)

            val added = users.add(user)
            if (added) {
                this.userAuth = userAuth
                if (onSuccessListener != null)
                    onSuccessListener?.onSuccess(userAuth)
            } else {
                this.userAuth = null
                if (onFailureListener != null)
                    onFailureListener?.onFailure(Error("Usuário já existe"))
            }

            if (onCompleteListener != null)
                onCompleteListener?.onComplete()
        }
    }

    fun login(email: String, password: String) {
        timeout {
            val userAuth = UserAuth(email, password)

            if (usersAuth.contains(userAuth)) {
                this.userAuth = userAuth
                onSuccessListener?.onSuccess(userAuth)
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

    interface OnSuccessListener {
        fun onSuccess(response: Any)
    }

    interface OnFailureListener {
        fun onFailure(e: Error)
    }

    interface OnCompleteListener {
        fun onComplete()
    }
}