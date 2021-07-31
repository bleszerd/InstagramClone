package com.github.bleszerd.instagramclone.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.DialogCustomBinding

/**
InstagramClone
31/07/2021 - 18:21
Created by bleszerd.
@author alive2k@programmer.net
 */
class CustomDialog : Dialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    private var titleId: Int = -1
    private lateinit var binding: DialogCustomBinding
    private lateinit var layoutParams: LinearLayout.LayoutParams
    private lateinit var dialogLinearLayout: LinearLayout
    private lateinit var dialogTitle: TextView
    private var textViews = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)

        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogLinearLayout = binding.dialogCustomLinearLayoutContainer
        dialogTitle = binding.dialogCustomTextViewDialogTitle
        layoutParams.setMargins(30, 50, 30, 50)

        setContentView(binding.root)
    }

    fun addButton(listener: View.OnClickListener, @StringRes vararg texts: Int) {
        for (i in texts.indices) {
            val textView =
                TextView(ContextThemeWrapper(context, R.style.InstaTextViewBaseDialog), null, 0)
            textView.id = texts[i]
            textView.setText(texts[i])
            textView.setOnClickListener { view ->
                listener.onClick(view)
                dismiss()
            }
            textViews.add(textView)
        }
    }

    override fun setTitle(@StringRes title: Int) {
        this.titleId = title
    }

    override fun show() {
        super.show()

        if (titleId == -1) {
            dialogTitle.visibility = View.GONE
        } else
            dialogTitle.setText(titleId)

        for (textView in textViews) {
            dialogLinearLayout.addView(textView)
        }
    }
}
