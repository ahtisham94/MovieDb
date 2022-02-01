package com.example.moviedb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.moviedb.callbacks.dialogCallback.DialogDismiss
import com.example.moviedb.dialogs.MyAltertDialog
import com.example.moviedb.dialogs.ProgressDialog
import com.example.moviedb.viewModels.BaseViewModel

abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity() {

    var myApplication: MyApplication? = null
    var progressDialog: ProgressDialog? = null
    protected var binding: Binding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myApplication = applicationContext as MyApplication
        myApplication?.setBaseActivity(this as BaseActivity<ViewDataBinding>)
    }

    protected fun bindView(layoutId: Int) {
        binding = DataBindingUtil.setContentView<Binding>(this, layoutId)
    }

    public fun setObserver(baseViewModel: BaseViewModel) {

        baseViewModel.errorLiveData.observe(this, Observer {
            Log.d("err", "onError: $it[0] + $it[1]")
            showAlterDialog(it[0].toString(), it[1].toString(), object : DialogDismiss {
                override fun onDismiss() {
                }
            })
        })

    }

    open fun onDismiss(params: Any?) {

    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showAlterDialog(code: String, message: String, dissmiss: DialogDismiss) {
        MyAltertDialog(this, code, message, dissmiss).show()
    }

    fun showProgressLoading(show: Boolean) {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        if (show) progressDialog!!.show() else progressDialog!!.dismiss()
    }
}