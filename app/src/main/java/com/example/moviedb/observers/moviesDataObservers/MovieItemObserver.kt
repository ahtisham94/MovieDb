package com.example.moviedb.observers.moviesDataObservers

import android.animation.ObjectAnimator
import android.os.Build
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.enumirations.MyUrls
import com.example.moviedb.enumirations.UrlsEnums
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*


class MovieItemObserver : BaseObservable() {

    var imageUrl: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageUrl)
        }

    var movieTile: String? = "MovieTitle"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.movieTile)
        }

    var movieReleaseDate: String? = "MovieReleaseDate"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.movieReleaseDate)
        }

    var movieDescription: String? = "Description"
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.movieDescription)
        }

    var movieRatings: Float? = 6.5f
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.movieRatings)
        }

    var movieRatingsInt: Int? = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.movieRatingsInt)
        }

    var movieRatingsText: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.movieRatingsText)
        }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["posterImage"])
        fun posterImage(posterImage: ImageView, url: String) {
            Glide.with(posterImage).load(UrlsEnums.IMAGE_BASE_URL.myUrl.url + url)
                .placeholder(R.drawable.clapperboard)
                .centerCrop().into(posterImage)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        @JvmStatic
        @BindingAdapter(value = ["releaseDateColor"])
        fun releaseDateColor(releaseDateYearTv: TextView, releaseDate: String) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd").parse(releaseDate)
            val calendar = Calendar.getInstance()
            calendar.time = dateFormat
            calendar.get(Calendar.YEAR)
            if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
                releaseDateYearTv.setTextColor(releaseDateYearTv.context.getColor(R.color.red))
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        @JvmStatic
        @BindingAdapter(value = ["setMaxProgress"])
        fun setMaxProgress(seekbar: ProgressBar, progress: Int) {
            ObjectAnimator.ofInt(seekbar, "progress", progress)
                .setDuration(1000)
                .start();
//            seekbar.progress = progress
        }
    }
}