package com.github.bleszerd.instagramclone.register.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.databinding.ActivityRegisterBinding
import com.github.bleszerd.instagramclone.main.presentation.MainActivity
import com.github.bleszerd.instagramclone.register.datasource.RegisterLocalDataSource

class RegisterActivity : AbstractActivity(), RegisterView {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter: RegisterPresenter

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setStatusBarDark()
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

        val layoutParams = binding.registerActivityScrollViewScrollView.layoutParams as LinearLayout.LayoutParams

        val frag = when (step) {
            RegisterSteps.EMAIL -> {
                layoutParams.gravity = Gravity.BOTTOM
                RegisterEmailFragment.newInstance(presenter)
            }
            RegisterSteps.NAME_PASSWORD -> {
                layoutParams.gravity = Gravity.BOTTOM
                RegisterNamePasswordFragment.newInstance(presenter)
            }
            RegisterSteps.WELCOME -> {
                layoutParams.gravity = Gravity.BOTTOM
                RegisterWelcomeFragment.newInstance(presenter)
            }
            RegisterSteps.PHOTO -> {
                layoutParams.gravity = Gravity.CENTER
                RegisterPhotoFragment.newInstance(presenter)
            }
        }

        binding.registerActivityScrollViewScrollView.layoutParams = layoutParams

        if (supportFragmentManager.findFragmentById(R.id.registerActivityFragmentFragmentHost) == null) {
            manager.add(R.id.registerActivityFragmentFragmentHost, frag, step.name)
        } else {
            manager.replace(R.id.registerActivityFragmentFragmentHost, frag, step.name)
            manager.addToBackStack(step.name)
        }

        manager.commit()
    }

    override fun onUserCreated() {
        MainActivity.launch(this)
    }

    override fun getContext(): Context {
        return applicationContext
    }
}