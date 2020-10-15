package com.montdeska.tmdb.ui.movies

import com.montdeska.tmdb.ui.data.models.Movie
import com.montdeska.tmdb.ui.data.models.Movies

interface MoviesContract {

    interface View {
        fun showPopular(movies: List<Movie>)
        fun showUpcoming(movies: List<Movie>)
        fun showLatest(movie: Movie)
        fun showError(message: String?)
    }


    interface Presenter {
        fun getPopular()
        fun getUpcoming()
        fun getLatest()
        fun detachView()
    }

    interface Model {
        suspend fun getPopularData(): Movies
        suspend fun getUpcomingData(): Movies
        suspend fun getLatestData(): Movie
    }
}