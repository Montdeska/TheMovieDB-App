package com.montdeska.tmdb.ui.movies

import com.montdeska.tmdb.ui.data.models.Movies
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesPresenter(
    private var view: MoviesContract.View,
    private var model: MoviesContract.Model,
    private val dispatcher: CoroutineDispatcher
) :
    CoroutineScope, MoviesContract.Presenter {

    private val job = Job()
    var _list: Movies? = null
    override val coroutineContext: CoroutineContext
        get() = job + dispatcher

    override fun getPopular() {
        launch {
            _list = model.getPopularData()
            if (_list!!.results.isNotEmpty()) {
                view.showPopular(_list!!.results)
            } else {
                view.showError("We couldn't get the popular movies")
            }
        }
    }

    override fun getUpcoming() {
        launch {
            _list = model.getUpcomingData()
            if (_list!!.results.isNotEmpty()) {
                view.showUpcoming(_list!!.results)
            } else {
                view.showError("We couldn't get the upcoming movies")
            }
        }
    }

    override fun getLatest() {
        launch {
            val movie = model.getLatestData()
            view.showLatest(movie)
        }
    }

    override fun detachView() {
        job.cancel()
    }


}