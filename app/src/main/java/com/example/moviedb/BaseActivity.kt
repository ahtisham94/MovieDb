package com.example.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.moviedb.viewModels.BaseViewModel

abstract class BaseActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setObserver(baseViewModel: BaseViewModel) {

        baseViewModel.errorLiveData.observe(this, Observer {

        })

    }
}