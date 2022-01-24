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
        path: String,
        id: String,
        pageNo: String,
        apiKey: String
    ) {
        registerAPIRequest(networkCallbacks, dataServices.getMoviesList(path, id, pageNo, apiKey))
    }
}