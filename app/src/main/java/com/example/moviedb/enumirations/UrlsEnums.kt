package com.example.moviedb.enumirations

enum class UrlsEnums(myUrls: MyUrls) {

    BASE_URL(MyUrls(" https://api.themoviedb.org/")),
    IMAGE_BASE_URL(MyUrls("https://image.tmdb.org/t/p/original"));
    var myUrl=myUrls
}    data class MyUrls(var url: String)
