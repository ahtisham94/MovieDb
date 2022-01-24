package com.example.moviedb.network

import com.example.moviedb.enumirations.UrlsEnums
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    fun create(): Retrofit? {

        if (retrofit == null) {

            val gson = GsonBuilder().setLenient()
                .create()
            val builder = Retrofit.Builder()
                .baseUrl(UrlsEnums.BASE_URL.myUrl.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
            okHttpClientBuilder.addInterceptor(interceptor)
            builder.client(okHttpClientBuilder.build())
            retrofit = builder.build()
        }

        return retrofit

    }


}