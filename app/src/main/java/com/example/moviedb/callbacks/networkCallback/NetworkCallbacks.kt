package com.example.moviedb.callbacks.networkCallback

interface NetworkCallbacks {

    fun onResult(result: Any)
    fun onError(code: Int, statusMessage: String)
}