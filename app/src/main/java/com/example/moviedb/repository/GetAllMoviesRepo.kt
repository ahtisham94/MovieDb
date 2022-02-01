package com.example.moviedb.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.moviedb.callbacks.networkCallback.NetworkCallbacks
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.network.GetDataServices
import com.example.moviedb.pagers.MoviePagingSource
import kotlinx.coroutines.flow.Flow

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

    fun getPagingMoviesList(): LiveData<PagingData<MovieDetailsModel>> {

        return Pager(config = PagingConfig(20)) {
            MoviePagingSource(dataServices)
        }.liveData

    }


    fun getPagingMovieList(): Flow<PagingData<MovieDetailsModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = {
                MoviePagingSource(dataServices)
            }
        ).flow
    }
}