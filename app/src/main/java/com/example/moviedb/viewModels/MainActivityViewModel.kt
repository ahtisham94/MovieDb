package com.example.moviedb.viewModels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.models.moviesModels.MoviesRootResponseModel
import com.example.moviedb.pagers.MoviePagingSource
import com.example.moviedb.repository.GetAllMoviesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Named

class MainActivityViewModel @ViewModelInject constructor(
    val getAllMoviesRepo: GetAllMoviesRepo,
    @Named("apiKey") val apiKey: String,
    val moviePagingSource: MoviePagingSource
) :
    BaseViewModel() {
    var arraylist: MutableLiveData<ArrayList<MovieDetailsModel>>? = null
    var movies: Flow<PagingData<MovieDetailsModel>>? = null


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
                val rootResponse = result as MoviesRootResponseModel
                arraylist?.postValue(rootResponse.result)
            }
        }, list)
    }

    fun getPagingData() {
//        movies = Pager(PagingConfig(pageSize = 20)) {
//            moviePagingSource
//        }.flow
//            .cachedIn(viewModelScope)

        movies = getAllMoviesRepo.getPagingMovieList().cachedIn(viewModelScope)
    }


}