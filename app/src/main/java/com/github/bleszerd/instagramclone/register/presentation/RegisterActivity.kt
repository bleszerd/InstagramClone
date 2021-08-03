package com.github.bleszerd.instagramclone.register.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.view.AbstractActivity
import com.github.bleszerd.instagramclone.databinding.ActivityRegisterBinding

class RegisterActivity : AbstractActivity(), RegisterView {
    private lateinit var binding: ActivityRegisterBinding

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
        val frag = RegisterEmailFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.registerActivityFragmentFragmentHost, frag, "fragment1")
            .commit()
    }

    override fun getContext(): Context {
        return applicationContext
    }
}