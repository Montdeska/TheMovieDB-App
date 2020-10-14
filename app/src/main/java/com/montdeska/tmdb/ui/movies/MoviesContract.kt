package com.montdeska.tmdb.ui.movies

import com.montdeska.tmdb.ui.data.models.Movie

interface MoviesContract {

    interface View {
        fun showPopular(movies: List<Movie>)
        fun showUpcoming(movies: List<Movie>)
        fun shoLatest(movies: List<Movie>)
    }

    interface Presenter {
        fun getPopular()
        fun getUpcoming()
        fun getLatest()
    }

    interface Model {
        suspend fun getPopularData()
        suspend fun getUpcomingData()
        suspend fun getLatestData()
    }
}