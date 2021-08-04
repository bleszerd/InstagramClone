package com.github.bleszerd.instagramclone.common.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.ButtonLoadingBinding

/**
InstagramClone
30/07/2021 - 14:13
Created by bleszerd.
@author alive2k@programmer.net
 */
class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ButtonLoadingBinding.inflate(LayoutInflater.from(context), this)
    private val button = getChildAt(0) as Button
    private val progressBar = getChildAt(1) as ProgressBar

    private var buttonText: String? = null

    init {
        setupComponent(attrs)
    }

    private fun setupComponent(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)

        //Init properties
        button.isEnabled = false

        //set progressbar loading icon color
        progressBar.indeterminateDrawable.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(R.color.white,
                BlendModeCompat.DST)

        //Get attrs
        buttonText = attributes.getString(R.styleable.LoadingButton_text)

        //Use attrs
        button.text = buttonText

        attributes.recycle()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }

    override fun setEnabled(enabled: Boolean) {
        button.isEnabled = enabled
        super.setEnabled(enabled)
    }

    fun showProgress(enabled: Boolean) {
        button.isClickable = !enabled
        button.text = if (enabled) "" else buttonText
        progressBar.visibility = if (enabled) VISIBLE else GONE
    }
}