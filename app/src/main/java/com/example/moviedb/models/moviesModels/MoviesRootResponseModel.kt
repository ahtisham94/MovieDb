package com.example.moviedb.models.moviesModels

import com.google.gson.annotations.SerializedName

data class MoviesRootResponseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String,
    @SerializedName("results") val result: ArrayList<MovieDetailsModel>
) {
}