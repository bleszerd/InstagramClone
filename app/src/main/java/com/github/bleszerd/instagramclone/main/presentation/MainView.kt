package com.github.bleszerd.instagramclone.main.presentation

import android.net.Uri
import com.github.bleszerd.instagramclone.common.models.Post
import com.github.bleszerd.instagramclone.common.view.View


/**
InstagramClone
06/08/2021 - 09:40
Created by bleszerd.
@author alive2k@programmer.net
 */
interface MainView : View {
    fun scrollToolbarEnabled(enabled: Boolean)

    interface ProfileView : View {
        fun showPhoto(photo: Uri)

        fun showData(name: String, following: String, followers: String, posts: String)

        fun showPosts(posts: MutableList<Post>)
    }
}