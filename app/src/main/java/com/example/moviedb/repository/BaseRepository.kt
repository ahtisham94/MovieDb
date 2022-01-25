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


                    response.body()?.let {
                        networkCallbacks.onResponse(
                            response.isSuccessful,
                            response.code(), response.message(), it
                        )
                    }
                } else networkCallbacks.onResponse(
                    false, response.code(),
                    when (response.message().isNullOrEmpty()) {
                        true -> "Server not responding"
                        false -> response.message()
                    }, null
                )


            }

            override fun onFailure(call: Call<t>, t: Throwable) {
                t.message?.let {
                    networkCallbacks.onResponse(
                        false, -1, it, null
                    )
                }
            }
        }
        )
    }
}