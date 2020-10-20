package com.montdeska.tmdb.ui.movies

import androidx.databinding.BindingAdapter
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

@BindingAdapter("movieImage")
fun bindMovieImage(view: RoundedImageView, movieURL: String?) {
    movieURL?.let {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/$movieURL")
            .into(view)
    }
}