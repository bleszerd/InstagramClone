package com.github.bleszerd.instagramclone.register.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.component.MediaHelper
import com.github.bleszerd.instagramclone.common.component.MediaHelper.Companion.OnImageCroppedListener
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.databinding.ActivityRegisterBinding
import com.github.bleszerd.instagramclone.main.presentation.MainActivity
import com.github.bleszerd.instagramclone.register.datasource.RegisterLocalDataSource

class RegisterActivity : AbstractActivity(), RegisterView, OnImageCroppedListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter: RegisterPresenter
    private lateinit var mediaHelper: MediaHelper.Companion

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registerActivityButtonButtonCrop.setOnClickListener {
            cropViewEnabled(false)
            MediaHelper.cropImage(binding.registerActivityCropImageViewImageCropper)
        }

        setStatusBarDark()
        mediaHelper = MediaHelper.apply {
            init(this@RegisterActivity)
            cropView(binding.registerActivityCropImageViewImageCropper)
            listener(this@RegisterActivity)
        }
    }

    override fun onInject() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val dataSource = RegisterLocalDataSource()

        presenter = RegisterPresenter(dataSource)
        presenter.setRegisterView(this)

        showNextView(RegisterSteps.EMAIL)
    }

    override fun showNextView(step: RegisterSteps) {
        val manager = supportFragmentManager.beginTransaction()

        val frag = when (step) {
            RegisterSteps.EMAIL -> {
                RegisterEmailFragment.newInstance(presenter)
            }
            RegisterSteps.NAME_PASSWORD -> {
                RegisterNamePasswordFragment.newInstance(presenter)
            }
            RegisterSteps.WELCOME -> {
                RegisterWelcomeFragment.newInstance(presenter)
            }
            RegisterSteps.PHOTO -> {
                val layoutParams =
                    binding.registerActivityScrollViewScrollView.layoutParams as FrameLayout.LayoutParams
                layoutParams.gravity = Gravity.CENTER
                binding.registerActivityScrollViewScrollView.layoutParams = layoutParams
                RegisterPhotoFragment.newInstance(presenter)
            }
        }

        if (supportFragmentManager.findFragmentById(R.id.registerActivityFragmentFragmentHost) == null) {
            manager.add(R.id.registerActivityFragmentFragmentHost, frag, step.name)
        } else {
            manager.replace(R.id.registerActivityFragmentFragmentHost, frag, step.name)
            manager.addToBackStack(step.name)
        }

        manager.commit()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cropViewEnabled(true)
        val mediaHelper = MediaHelper.apply {
            setActivity(this@RegisterActivity)
            onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun cropViewEnabled(enabled: Boolean) {
        binding.registerActivityCropImageViewImageCropper.visibility =
            if (enabled) View.VISIBLE else View.GONE
        binding.registerActivityScrollViewScrollView.visibility =
            if (enabled) View.GONE else View.VISIBLE
        binding.registerActivityButtonButtonCrop.visibility =
            if (enabled) View.VISIBLE else View.GONE
        binding.registerActivityFrameLayoutRootContainer.setBackgroundColor(if (enabled) findColor(R.color.black) else findColor(
            R.color.white))
    }

    override fun onUserCreated() {
        MainActivity.launch(this)
    }

    override fun showCamera() {
        mediaHelper.apply {
            chooserCamera()
        }
    }

    override fun showGallery() {
        mediaHelper.apply {
            chooserGallery()
        }

    }

    override fun getContext(): Context {
        return applicationContext
    }

    override fun onImageCropped(uri: Uri) {
        presenter.setUri(uri)
    }

    override fun onImagePicked(uri: Uri?) {
        binding.registerActivityCropImageViewImageCropper.setImageUriAsync(uri)
    }
}