package com.montdeska.tmdb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montdeska.tmdb.BuildConfig
import kotlinx.coroutines.launch

class MoviesPresenter(var view: MoviesContract.View) : ViewModel(), MoviesContract.Presenter {


    private var headers = HashMap<String, String>()
    private lateinit var model: MoviesModel

    init {
        headers.put("Authorization", BuildConfig.TOKEN)
        MoviesModel(headers)
    }

    private val _movieslist = MutableLiveData<String>()
    val moviesList: LiveData<String> get() = _movieslist

    override fun getPopular() {
        viewModelScope.launch {
            model.getPopularData()
        }
    }

    override fun getUpcoming() {

    }

    override fun getLatest() {

    }


}