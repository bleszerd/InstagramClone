package com.github.bleszerd.instagramclone.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.bleszerd.instagramclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}