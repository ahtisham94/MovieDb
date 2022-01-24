package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviedb.viewModels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    @Inject
    lateinit var apiKey: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setObservers()
    }

    private fun setObservers() {
        mainActivityViewModel.getMoviesList(apiKey)
    }
}