package com.github.bleszerd.instagramclone.main.profile.presentation

import com.github.bleszerd.instagramclone.common.models.UserProfile
import com.github.bleszerd.instagramclone.common.presenter.Presenter
import com.github.bleszerd.instagramclone.main.presentation.MainView
import com.github.bleszerd.instagramclone.main.profile.datasource.ProfileDataSource

class ProfilePresenter(dataSource: ProfileDataSource) : Presenter {
    private lateinit var view: MainView.ProfileView
    private var dataSource: ProfileDataSource = dataSource

    override fun onSuccess(response: Any) {
        val userProfile = response as UserProfile
        val user = userProfile.user
        val posts = userProfile.posts

        view.showData(
            user.name,
            user.following.toString(),
            user.followers.toString(),
            user.posts.toString()
        )

        println(posts)
        view.showPosts(userProfile.posts)

        if (user.uri != null){
            view.showPhoto(user.uri!!)
        }
    }

    override fun onError(message: String?) {
        // TODO: 06/08/2021
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

    fun setView(view: MainView.ProfileView) {
        this.view = view
    }

    fun findUsers() {
        view.showProgressBar()
        dataSource.findUser(this)
    }

}
