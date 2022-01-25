package com.example.moviedb.fragments.mainActivityFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviedb.MainActivity
import com.example.moviedb.R
import com.example.moviedb.callbacks.GenericCallback
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import kotlinx.android.synthetic.main.fragment_main_activity.*
import java.lang.Exception

class MoviesListFragment : Fragment(), GenericCallback {
    var mainActivity: MainActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            mainActivity = activity as MainActivity
            moviesListRecycler.adapter = mainActivity!!.adapter
            mainActivity!!.adapter.setCallback(this)
            mainActivity?.setToobarIcons(false)
        } catch (e: Exception) {

        }

    }

    override fun genericResponse(t: Any) {
        if (t is MovieDetailsModel) {
            mainActivity?.closeSearchView()
            findNavController().navigate(
                MoviesListFragmentDirections.gotoMovieDetailsFragmentAction(
                    t
                )
            )
        }
    }
}