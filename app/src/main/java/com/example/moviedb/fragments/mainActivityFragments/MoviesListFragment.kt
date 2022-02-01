package com.example.moviedb.fragments.mainActivityFragments

import android.os.Binder
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.moviedb.BaseActivity
import com.example.moviedb.MainActivity
import com.example.moviedb.R
import com.example.moviedb.adapters.moviesList.MoviesListAdapter
import com.example.moviedb.callbacks.GenericCallback
import com.example.moviedb.models.moviesModels.MovieDetailsModel
import com.example.moviedb.viewModels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_activity.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class MoviesListFragment : Fragment(), GenericCallback {
    var mainActivity: MainActivity? = null
    val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    @Inject
    lateinit var adapter: MoviesListAdapter
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
            moviesListRecycler.adapter = adapter
            adapter.setCallback(this)
            mainActivity?.setToobarIcons(false)
            mainActivity?.showSreachView(true)
            setObservers()
        } catch (e: Exception) {

        }

    }

    @ExperimentalCoroutinesApi
    private fun setObservers() {
        //        mainActivityViewModel.getMoviesList<Any>()
        activity?.let {
            mainActivityViewModel.arraylist?.observe(it, Observer {
                //            adapter.submitList(it)
                //            adapter.setList(it)
            })
        }
        mainActivityViewModel.getPagingData()
        lifecycleScope.launch {
            mainActivityViewModel.movies?.collectLatest {
                Log.d("it", "setObservers: ${it}")
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {

            adapter?.loadStateFlow?.collectLatest {
                if (it.refresh is LoadState.Loading) {
                    (mainActivity as BaseActivity<ViewDataBinding>)
                        .showProgressLoading(true)
                } else if (it.refresh is LoadState.NotLoading) {
                    (mainActivity as BaseActivity<ViewDataBinding>)
                        .showProgressLoading(false)
                }

            }
        }
    }

    override fun genericResponse(t: Any) {
        if (t is MovieDetailsModel) {
            mainActivity?.closeSearchView()
            mainActivity?.showSreachView(false)
            findNavController().navigate(
                MoviesListFragmentDirections.gotoMovieDetailsFragmentAction(
                    t
                )
            )
        }
    }
}