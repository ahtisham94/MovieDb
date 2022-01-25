package com.example.moviedb.adapters.moviesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.callbacks.GenericCallback
import com.example.moviedb.databinding.MovieItemLayoutBinding
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.observers.moviesDataObservers.MovieItemObserver
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class MoviesListAdapter @Inject constructor(var moviesList: ArrayList<MovieDetailsModel>) :
    RecyclerView.Adapter<MoviesListAdapter.MyMoviesListHolder>() {

    var copyList: ArrayList<MovieDetailsModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMoviesListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(layoutInflater, parent, false)
        return MyMoviesListHolder(binding)
    }

    override fun onBindViewHolder(holder: MyMoviesListHolder, position: Int) {
        moviesList.get(holder.adapterPosition)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setList(arrayList: ArrayList<MovieDetailsModel>) {
        copyList = arrayList
        moviesList = arrayList
        notifyDataSetChanged()
    }

    var genericCallback: GenericCallback? = null

    fun setCallback(genericCallback: GenericCallback) {
        this.genericCallback = genericCallback
    }

    fun filter(filter: String) {
        moviesList = arrayListOf()
        if (filter.isEmpty())
            copyList?.let { moviesList.addAll(it) }
        else {
            for (item in copyList!!) {
                if (item.title.toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                    moviesList.add(item)
                }
            }
        }
        notifyDataSetChanged()
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