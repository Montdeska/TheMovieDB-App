package com.montdeska.tmdb.ui.movies

import com.montdeska.tmdb.ui.data.models.Movies
import com.montdeska.tmdb.ui.data.remote.Api

class MoviesModel(val headers: HashMap<String, String>) : MoviesContract.Model {

    override suspend fun getPopularData(): Movies {
        return Api.retrofitService.getPopular(headers)
    }

    override suspend fun getUpcomingData() {

    }

    override suspend fun getLatestData() {

    }

}