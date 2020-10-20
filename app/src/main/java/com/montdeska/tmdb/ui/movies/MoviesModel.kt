package com.montdeska.tmdb.ui.movies

import com.montdeska.tmdb.data.models.Movie
import com.montdeska.tmdb.data.models.Movies
import com.montdeska.tmdb.data.remote.Api

class MoviesModel(private val headers: HashMap<String, String>) : MoviesContract.Model {

    override suspend fun getPopularData(): Movies {
        return Api.retrofitService.getPopular(headers)
    }

    override suspend fun getUpcomingData(): Movies {
        return Api.retrofitService.getUpcoming(headers)
    }

    override suspend fun getLatestData(): Movie {
        return Api.retrofitService.getLatest(headers)
    }

}