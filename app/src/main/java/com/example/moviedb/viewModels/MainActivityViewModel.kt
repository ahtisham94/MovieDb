package com.example.moviedb.viewModels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.models.moviesModels.MoviesRootResponseModel
import com.example.moviedb.repository.GetAllMoviesRepo
import javax.inject.Named

class MainActivityViewModel @ViewModelInject constructor(
    val getAllMoviesRepo: GetAllMoviesRepo, @Named("apiKey") val apiKey: String
) :
    BaseViewModel() {
    var arraylist: MutableLiveData<ArrayList<MovieDetailsModel>>? = null

    init {
        arraylist = MutableLiveData()
    }

    fun <t> getMoviesList() {
        Log.d("key", "getMoviesList: $apiKey")
        val list = arrayListOf<Any>()
        list.add("3")
        list.add("popular")
        list.add(apiKey)
        list.add("en")
        list.add("1")
        callToRepo(getAllMoviesRepo, "getMoviesList", object : NetworkCallbacks {
            override fun onResponse(success: Boolean, code: Int, message: String, result: Any?) {
                val rootResponse=result as MoviesRootResponseModel
                arraylist?.postValue(rootResponse.result)
            }
        }, list)
    }
}