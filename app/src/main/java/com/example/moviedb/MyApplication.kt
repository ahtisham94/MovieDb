package com.example.moviedb

import android.app.Activity
import android.app.Application
import androidx.databinding.ViewDataBinding
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    private var baseActivity: BaseActivity<ViewDataBinding>? = null

    companion object {
        var INSTANCE: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    fun setBaseActivity(baseActivity: BaseActivity<ViewDataBinding>) {
        this.baseActivity = baseActivity
    }

    fun getBaseActivity(): BaseActivity<ViewDataBinding> {
        return this.baseActivity!!
    }


}