package com.example.moviedb.viewModels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
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
            networkCallbacks.javaClass,
            arrayList.javaClass
        )

        method.invoke(repo, object : NetworkCallbacks {
            override fun onResponse(success: Boolean, code: Int, message: String, result: Any?) {
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