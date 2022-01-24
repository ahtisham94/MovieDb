package com.example.moviedb.repository

import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseRepository {


    fun <t> registerAPIRequest(networkCallbacks: NetworkCallbacks, call: Call<t>) {
        call.enqueue(object : Callback<t> {
            override fun onResponse(call: Call<t>, response: Response<t>) {
                if (response.code() == 200) {
                    response.body()?.let { networkCallbacks.onResult(it) }
                } else networkCallbacks.onError(response.code(), response.message())

            }

            override fun onFailure(call: Call<t>, t: Throwable) {
                t.message?.let { networkCallbacks.onError(-1, it) }
            }
        }
        )
    }
}