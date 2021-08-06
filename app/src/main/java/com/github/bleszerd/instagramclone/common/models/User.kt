package com.github.bleszerd.instagramclone.common.models

import android.net.Uri

/**
InstagramClone
04/08/2021 - 08:14
Created by bleszerd.
@author alive2k@programmer.net
 */
data class User(
    val email: String,
    val name: String,
    var uuid: String?
): GenericModel{
    lateinit var uri: Uri

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (email != other.email) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}