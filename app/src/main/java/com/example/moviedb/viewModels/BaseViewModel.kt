package com.example.moviedb.viewModels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.MyApplication
import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
import com.example.moviedb.dialogs.ProgressDialog
import java.lang.reflect.Method

abstract class BaseViewModel : ViewModel() {

    var errorLiveData =
        MutableLiveData<ArrayList<Any>>()
        protected set

    fun <t : Any> callToRepo(
        repo: t, methodName: String, networkCallbacks: NetworkCallbacks, arrayList: ArrayList<Any>
    ) {

        val method: Method
        method = repo.javaClass.getDeclaredMethod(
            methodName,
            NetworkCallbacks::class.java,
            ArrayList::class.java

        )
        var progressDialog: ProgressDialog? = null
        if (MyApplication.INSTANCE?.getBaseActivity() != null) {
            MyApplication.INSTANCE!!.getBaseActivity().runOnUiThread(Runnable {
                progressDialog = ProgressDialog(MyApplication.INSTANCE!!.getBaseActivity())
                progressDialog?.show()
            })
        }
        method.invoke(repo, object : NetworkCallbacks {
            override fun onResponse(
                success: Boolean,
                code: Int,
                message: String,
                result: Any?
            ) {
                if (MyApplication.INSTANCE?.getBaseActivity() != null && progressDialog != null) {
                    MyApplication.INSTANCE?.getBaseActivity()!!.runOnUiThread(Runnable {
                        progressDialog?.dismiss()
                    })
                }
                if (success) {
                    networkCallbacks.onResponse(success, code, message, result)
                } else {
                    val list = arrayListOf<Any>()
                    list.add(code)
                    list.add(message)
                    errorLiveData.postValue(list)
                }
            }


        }, arrayList)
    }

}