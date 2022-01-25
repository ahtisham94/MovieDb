package com.example.moviedb.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.moviedb.R
import kotlinx.android.synthetic.main.movie_dialog_layout.*

class ProgressDialog(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.movie_dialog_layout)
        setCancelable(false)
        setOnCancelListener(null)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        animationView.playAnimation()
    }

    override fun dismiss() {
        super.dismiss()
        animationView.cancelAnimation()
    }
}
