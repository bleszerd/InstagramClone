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
    private val posts: HashMap<String, HashSet<Post>> = HashMap()

    private var onFailureListener: OnFailureListener? = null
    private var onCompleteListener: OnCompleteListener? = null
    private var onSuccessListener: OnSuccessListener? = null

    init {
//        usersAuth.add(UserAuth("email@example.com", "example"))

        val email = "user1@gmail.com"
        val password = "123"
        val name = "user1"
        val userAuth = UserAuth(email, password)
        val user = User(email, name, null, 0, 0, 0, userAuth.getUUID())

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
            val user = User(email, name, null, 0, 0, 0, null)

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

    fun findPosts(uuid: String) {
        timeout {
            val posts = Database.posts
            var res = posts[uuid]

            if (res == null)
                res = HashSet()

            if (onSuccessListener != null)
                onSuccessListener?.onSuccess(ArrayList(res))

            if (onCompleteListener != null)
                onCompleteListener?.onComplete()
        }
    }

    fun findUser(uuid: String) {
        timeout {
            val users = Database.users
            var res: User? = null
            users.forEach { user ->
                if (user.uuid.equals(uuid)) {
                    res = user
                    return@forEach
                }
            }

            if (onSuccessListener != null && res != null)
                onSuccessListener?.onSuccess(res!!)
            else if (onFailureListener != null) {
                onFailureListener?.onFailure(Error("Usuário não encontrado"))
            }

            if (onCompleteListener != null)
                onCompleteListener?.onComplete()
        }
    }

    private fun timeout(r: Runnable) {
        Handler(Looper.getMainLooper()).postDelayed({
            r.run()
        }, 1000)
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