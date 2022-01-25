package com.example.moviedb.adapters.moviesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.callbacks.GenericCallback
import com.example.moviedb.databinding.MovieItemLayoutBinding
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.observers.moviesDataObservers.MovieItemObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesListAdapter @Inject constructor(var moviesList: ArrayList<MovieDetailsModel>) :
    RecyclerView.Adapter<MoviesListAdapter.MyMoviesListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMoviesListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(layoutInflater, parent, false)
        return MyMoviesListHolder(binding)
    }

    override fun onBindViewHolder(holder: MyMoviesListHolder, position: Int) {
        holder.bind(moviesList.get(holder.adapterPosition))
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setList(arrayList: ArrayList<MovieDetailsModel>) {
        moviesList = arrayList
        notifyDataSetChanged()
    }

    var genericCallback: GenericCallback? = null

    fun setCallback(genericCallback: GenericCallback) {
        this.genericCallback = genericCallback
    }

    inner class MyMoviesListHolder(itemView: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(movieDetailsModel: MovieDetailsModel) {
            val movieItemObserver = MovieItemObserver()
            movieItemObserver.imageUrl = movieDetailsModel.posterPath
            movieItemObserver.movieDescription = movieDetailsModel.overview
            movieItemObserver.movieTile = movieDetailsModel.title
            movieItemObserver.movieRatings = movieDetailsModel.voteAverage
            movieItemObserver.movieReleaseDate = movieDetailsModel.releaseDate
            binding.container.setOnClickListener {
                genericCallback?.genericResponse(movieDetailsModel)
            }
            binding.movieDetails = movieItemObserver
            binding.executePendingBindings()
        }
    }
}