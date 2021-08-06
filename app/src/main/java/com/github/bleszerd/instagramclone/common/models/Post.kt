package com.github.bleszerd.instagramclone.common.models

import android.net.Uri

/**
InstagramClone
06/08/2021 - 12:19
Created by bleszerd.
@author alive2k@programmer.net
 */
data class Post(
    var uuid: String,
    var uri: Uri,
    var caption: String,
    var timestamp: Long,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (uri != other.uri) return false
        if (caption != other.caption) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uri.hashCode()
        result = 31 * result + caption.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}
