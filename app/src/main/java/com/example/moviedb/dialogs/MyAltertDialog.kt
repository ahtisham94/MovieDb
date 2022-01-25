package com.example.moviedb.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import com.example.moviedb.R
import com.example.moviedb.callbacks.dialogCallback.DialogDismiss
import kotlinx.android.synthetic.main.success_dialog_layout.*

class MyAltertDialog(
    context: Context, code: String, message: String,
    altertCallback: DialogDismiss
) : Dialog(context,R.style.ErrorDialog) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        setContentView(R.layout.success_dialog_layout)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = lp
        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        lbl_success_title.text = code
        lbl_success_desc.text = message
        btn_ok.setOnClickListener {
            altertCallback.onDismiss()
            dismiss()
        }
    }
}