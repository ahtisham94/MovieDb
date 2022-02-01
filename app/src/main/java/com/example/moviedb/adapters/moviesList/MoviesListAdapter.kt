package com.example.moviedb.adapters.moviesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
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
class MoviesListAdapter @Inject constructor() :
    PagingDataAdapter<MovieDetailsModel, MoviesListAdapter.MyMoviesListHolder>(MyDiffUtils()) {
    var filterList: MutableList<MovieDetailsModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMoviesListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(layoutInflater, parent, false)
        return MyMoviesListHolder(binding)
    }

    override fun onBindViewHolder(holder: MyMoviesListHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    //    override fun getItemCount(): Int {
//        return moviesList.size
//    }
//
//    override fun getItemId(position: Int): Long {
//        return super.getItemId(position)
//    }
//
    fun setList(arrayList: List<MovieDetailsModel>) {
        filterList = mutableListOf()
        filterList!!.addAll(arrayList)
    }

    //
    override fun getItemViewType(position: Int): Int {
        return position
    }

    var genericCallback: GenericCallback? = null

    fun setCallback(genericCallback: GenericCallback) {
        this.genericCallback = genericCallback
    }

//    fun filter(filter: String) {
////        moviesList   = arrayListOf()
//        if (filter.isEmpty())
//            copyList?.let { currentList.addAll(it) }
//        else {
//            for (item in copyList!!) {
//                if (item.title.toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
//                    currentList.add(item)
//                }
//            }
//        }
//        notifyDataSetChanged()
//    }

  /*  fun filterData(filter: String?) {
        var list= mutableListOf<MovieDetailsModel>()
        if (!filter.isNullOrEmpty()) {
            for (i in filterList!!) {
                if (i.title.toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                    list!!.add(i)
                }
            }
        } else {
            filterList?.let { list.addAll(it) }

        }
        submitList(list)
    }*/

    class MyDiffUtils : DiffUtil.ItemCallback<MovieDetailsModel>() {
        override fun areItemsTheSame(
            oldItem: MovieDetailsModel,
            newItem: MovieDetailsModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieDetailsModel,
            newItem: MovieDetailsModel
        ): Boolean {
            return oldItem.title == newItem.title && oldItem.posterPath == newItem.posterPath
        }

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
                notifyDataSetChanged()
            }
            binding.movieDetails = movieItemObserver
            binding.executePendingBindings()
        }
    }

}