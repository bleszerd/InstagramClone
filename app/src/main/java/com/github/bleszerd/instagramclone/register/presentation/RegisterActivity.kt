package com.github.bleszerd.instagramclone.register.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.databinding.ActivityRegisterBinding
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
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarDark()
    }

    override fun onInject() {
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
//            RegisterSteps.PHOTO -> {
//
//            }
            else -> {
                RegisterEmailFragment.newInstance(presenter)
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

    override fun getContext(): Context {
        return applicationContext
    }
}