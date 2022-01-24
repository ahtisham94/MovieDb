package com.example.moviedb.viewModels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.repository.GetAllMoviesRepo
import kotlin.math.log

class MainActivityViewModel @ViewModelInject constructor(
    val getAllMoviesRepo: GetAllMoviesRepo
) :
    BaseViewModel() {
    var list: MutableLiveData<ArrayList<MovieDetailsModel>>? = null

    init {
        list = MutableLiveData()
    }

    fun getMoviesList(apiKey: String) {
        Log.d("key", "getMoviesList: $apiKey")
        getAllMoviesRepo.getMoviesList<Any>(
            object : NetworkCallbacks {
                override fun onResult(result: Any) {
                    Log.d("ok", "onResult: ok ")
                }

                override fun onError(code: Int, statusMessage: String) {
                    Log.d("err", "onError: $code + $statusMessage")
                }
            },
            "4", "1", "1", apiKey
        )
    }
}