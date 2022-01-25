package com.example.moviedb.di.modules

import android.content.Context
import com.example.moviedb.R
import com.example.moviedb.enumirations.UrlsEnums
import com.example.moviedb.network.GetDataServices
import com.example.moviedb.network.RetrofitClientInstance
import com.example.moviedb.repository.GetAllMoviesRepo
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(UrlsEnums.BASE_URL.myUrl.url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()

    }

    @Singleton
    @Provides
    fun getDataService(retrofit: Retrofit): GetDataServices {
        return retrofit.create(GetDataServices::class.java)
    }

    @Singleton
    @Provides
    @Named("apiKey")
    fun getApiKey(@ApplicationContext context: Context): String {
        return context.getString(R.string.api_key)
    }

}
