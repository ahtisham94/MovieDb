package com.example.moviedb.di.modules

import com.example.moviedb.adapters.moviesList.MoviesListAdapter
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.network.GetDataServices
import com.example.moviedb.repository.GetAllMoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named


@Module
@InstallIn(ActivityComponent::class)
object MainActivityModules {

    @ActivityScoped
    @Provides
    @Named("movieRepo")
    fun getMoviesListRepo(dataServices: GetDataServices): GetAllMoviesRepo {
        return GetAllMoviesRepo(dataServices)
    }

    @ActivityScoped
    @Provides
    fun getMoviesAdapter(): MoviesListAdapter {
        return MoviesListAdapter(arrayListOf())
    }

}