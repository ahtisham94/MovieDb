package com.example.moviedb.network

import com.example.moviedb.models.moviesModels.MoviesRootResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetDataServices {

    @GET("{type}/list/{id}")
    fun getMoviesList(
        @Path("type") path: String,
        @Path("id") id: String,
        @Query("page") pageNo: String,
        @Query("api_key") apiKey: String
    ): Call<MoviesRootResponseModel>
}
