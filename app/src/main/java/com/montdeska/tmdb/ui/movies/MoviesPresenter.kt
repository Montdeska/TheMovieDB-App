package com.montdeska.tmdb.ui.movies

import com.montdeska.tmdb.BuildConfig
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesPresenter(var view: MoviesContract.View) : CoroutineScope, MoviesContract.Presenter {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private var headers = HashMap<String, String>()
    private var model: MoviesModel

    init {
        headers["Authorization"] = "Bearer " + BuildConfig.TOKEN
        model = MoviesModel(headers)
    }

    override fun getPopular() {
        launch {
            val list = model.getPopularData()
            if (list.results.isNotEmpty()) {
                view.showPopular(list.results)
            } else {
                view.showError("No pudimos obtener las películas populares.")
            }
        }
    }

    override fun getUpcoming() {

    }

    override fun getLatest() {

    }

    override fun detachView() {
        job.cancel()
    }


}