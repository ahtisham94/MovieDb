package com.example.moviedb.callbacks.networkCallback

interface NetworkCallbacks {

    fun onResponse(success:Boolean,code: Int,message:String,result: Any?)
}