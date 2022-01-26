package com.example.moviedb.fragments.mainActivityFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.moviedb.MainActivity

import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentMovieDetailsBinding
import com.example.moviedb.observers.moviesDataObservers.MovieItemObserver
import java.lang.Exception

class MovieDetailsFragment : Fragment() {
    val args: MovieDetailsFragmentArgs by navArgs()
    var binding: FragmentMovieDetailsBinding? = null
    var mainActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            mainActivity = activity as MainActivity
            mainActivity?.setToobarIcons(true)
            val movieItemObserver = MovieItemObserver()
            movieItemObserver.movieReleaseDate = args.movieDetails.releaseDate
            movieItemObserver.movieRatings = args.movieDetails.voteAverage
            movieItemObserver.movieRatingsInt = (args.movieDetails.voteAverage * 10.0f).toInt()
            movieItemObserver.movieRatingsText = (args.movieDetails.voteAverage * 10.0f).toString() + "%"
            movieItemObserver.movieTile = args.movieDetails.title
            movieItemObserver.movieDescription = args.movieDetails.overview
            movieItemObserver.imageUrl = args.movieDetails.posterPath
            binding?.movieDetails = movieItemObserver
            binding?.backBtn?.setOnClickListener { activity?.onBackPressed() }
        }catch (e:Exception){}

    }

}