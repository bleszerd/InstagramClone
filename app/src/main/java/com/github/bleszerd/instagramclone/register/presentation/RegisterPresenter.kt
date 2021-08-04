package com.github.bleszerd.instagramclone.register.presentation

import android.net.Uri
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.models.GenericModel
import com.github.bleszerd.instagramclone.common.presenter.Presenter
import com.github.bleszerd.instagramclone.common.utils.Strings
import com.github.bleszerd.instagramclone.register.datasource.RegisterLocalDataSource

/**
InstagramClone
03/08/2021 - 15:18
Created by bleszerd.
@author alive2k@programmer.net
 */
class RegisterPresenter(private val dataSource: RegisterLocalDataSource) : Presenter{
    private lateinit var photoView: RegisterView.PhotoView
    private lateinit var registerView: RegisterView
    private lateinit var namePasswordView: RegisterView.NamePasswordView
    private lateinit var emailView: RegisterView.EmailView

    private lateinit var email: String
    private lateinit var uri: Uri
    lateinit var name: String

    fun setRegisterView(registerView: RegisterView){
        this.registerView = registerView
    }

    fun setUri(uri: Uri){
        this.uri = uri
        photoView.onImageCropped(uri)
    }

    fun setEmailView(emailView: RegisterView.EmailView) {
        this.emailView = emailView
    }

    fun showCamera(){
        registerView.showCamera()
    }

    fun showGallery(){
        registerView.showGallery()
    }

    fun setNamePasswordView(namePasswordView: RegisterView.NamePasswordView){
        this.namePasswordView = namePasswordView
    }

    fun setPhotoView(photoView: RegisterView.PhotoView){
        this.photoView = photoView
    }

    fun setEmail(email: String){
        if(!Strings.emailIsValid(email)){
            emailView.onFailureForm(emailView.getContext().getString(R.string.invalid_email))
            return
        }

        this.email = email
        registerView.showNextView(RegisterSteps.NAME_PASSWORD)
    }

    fun setNameAndPassword(name: String, password: String, confirmPassword: String){
        if(password != confirmPassword){
            namePasswordView.onFailureForm(null, namePasswordView.getContext().getString(R.string.password_not_equal))
            return
        }

        this.name = name

        namePasswordView.showProgressBar()
        dataSource.createUser(name, email, password, this)
    }

    override fun onSuccess(response: GenericModel) {
        registerView.showNextView(RegisterSteps.WELCOME)
    }

    override fun onError(message: String?) {
        namePasswordView.onFailureCreateUser(message ?: "")
    }

    override fun onComplete() {
        namePasswordView.hideProgressBar()
    }

    fun showPhotoView() {
        registerView.showNextView(RegisterSteps.PHOTO)
    }

    fun jumpRegistration(){
        registerView.onUserCreated()
    }
}