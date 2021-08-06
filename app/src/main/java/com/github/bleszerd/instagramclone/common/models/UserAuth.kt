package com.github.bleszerd.instagramclone.common.models

/**
InstagramClone
03/08/2021 - 10:33
Created by bleszerd.
@author alive2k@programmer.net
 */

data class UserAuth(
    var email: String,
    var password: String,
){
    fun getUUID(): String{
        return hashCode().toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserAuth

        if (email != other.email) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}