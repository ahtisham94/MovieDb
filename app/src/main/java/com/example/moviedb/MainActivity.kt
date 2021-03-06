package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.moviedb.adapters.moviesList.MoviesListAdapter
import com.example.moviedb.viewModels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : BaseActivity<ViewDataBinding>(), SearchView.OnQueryTextListener {

    public val mainActivityViewModel: MainActivityViewModel by viewModels()
    var searchView: SearchView? = null
    var searchItem: MenuItem? = null
    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)
        setObservers()
        setObserver(mainActivityViewModel)
        setSupportActionBar(toolbar as Toolbar)
        setTitle("Movies DB")
    }
    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    private fun setObservers() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchbar_menu, menu)
        searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.search)
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        showToast("Query Inserted $query")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
//        adapter.filterData(newText!!)
        return true
    }

    fun closeSearchView() {
        searchView?.onActionViewCollapsed()
        searchView?.visibility = View.GONE
    }

    fun setToobarIcons(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        supportActionBar?.setDisplayShowHomeEnabled(show)
    }

    fun showSreachView(visible: Boolean) {
        searchItem?.setVisible(visible)
    }


}