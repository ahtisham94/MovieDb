package com.example.moviedb.observers.moviesDataObservers

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

    var backgroundPoster: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.backgroundPoster)
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

    var showImageProgress: Boolean? = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.showImageProgress)
        }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["posterImage"])
        fun posterImage(posterImage: ImageView, url: String) {
            Glide.with(posterImage).load(UrlsEnums.IMAGE_BASE_URL.myUrl.url + url)
                .placeholder(R.drawable.clapperboard)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .centerCrop().into(posterImage)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        @JvmStatic
        @BindingAdapter(value = ["releaseDateColor"])
        fun releaseDateColor(releaseDateYearTv: TextView, releaseDate: String?) {
            if (!releaseDate.isNullOrEmpty()) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd").parse(releaseDate)
                val calendar = Calendar.getInstance()
                calendar.time = dateFormat
                calendar.get(Calendar.YEAR)
                if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
                    releaseDateYearTv.setTextColor(releaseDateYearTv.context.getColor(R.color.red))
                }
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

        @RequiresApi(Build.VERSION_CODES.M)
        @JvmStatic
        @BindingAdapter(value = ["setAverageInText"])
        fun setAverageInText(textView: TextView, progress: Int) {
            val animator = ObjectAnimator.ofInt(0, progress)
                .setDuration(1000)
            animator.addUpdateListener {
                textView.text = it.animatedValue.toString() + "%"
            }
            animator.start()
//            seekbar.progress = progress
        }
    }
}