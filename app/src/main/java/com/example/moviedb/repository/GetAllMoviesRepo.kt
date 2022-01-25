package com.example.moviedb.repository

import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
import com.example.moviedb.network.GetDataServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetAllMoviesRepo @Inject constructor(private val dataServices: GetDataServices) :
    BaseRepository() {


    fun <t> getMoviesList(
        networkCallbacks: NetworkCallbacks,
        list: ArrayList<Any>
    ) {

        registerAPIRequest(
            networkCallbacks, dataServices.getMoviesList(
                list[0] as String, list[1] as String, list[2] as String,
                list[3] as String,
                list[4] as String
            )
        )
    }
}