package com.example.moviedb.pagers

import android.util.Log
import androidx.paging.PagingSource
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.models.moviesModels.MoviesRootResponseModel
import com.example.moviedb.network.GetDataServices
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Named

class MoviePagingSource @Inject constructor(
    val dataServices: GetDataServices
) :
    PagingSource<Int, MovieDetailsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailsModel> {
        try {

            val nextPage = params.key ?: 1
            val response  =
                dataServices.getMoviesListPaging(
                    "3",
                    "popular",
                    "83d01f18538cb7a275147492f84c3698",
                    "en",
                    nextPage.toString()
                )

            val items = response.result
            return LoadResult.Page(
                data = items,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = (response as MoviesRootResponseModel).page + 1
            )
        } catch (e: Exception) {
            Log.d("exxx", "load: ${e.message}")
            return LoadResult.Error(e)
        }

    }
}