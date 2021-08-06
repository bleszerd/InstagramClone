package com.github.bleszerd.instagramclone.main.profile.datasource

import com.github.bleszerd.instagramclone.common.presenter.Presenter
import com.github.bleszerd.instagramclone.main.profile.presentation.ProfilePresenter

/**
InstagramClone
06/08/2021 - 12:31
Created by bleszerd.
@author alive2k@programmer.net
 */
interface ProfileDataSource {

    fun findUser(presenter: Presenter)
}