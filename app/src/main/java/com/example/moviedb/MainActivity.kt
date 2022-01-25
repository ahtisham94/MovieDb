package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.moviedb.adapters.moviesList.MoviesListAdapter
import com.example.moviedb.viewModels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : BaseActivity<ViewDataBinding>() {

    public val mainActivityViewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var adapter: MoviesListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)
        setObservers()
        setObserver(mainActivityViewModel)
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        setTitle("Movies DB")
    }

    private fun setObservers() {
        mainActivityViewModel.getMoviesList<Any>()
        mainActivityViewModel.arraylist?.observe(this, Observer {
            adapter.setList(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }

}