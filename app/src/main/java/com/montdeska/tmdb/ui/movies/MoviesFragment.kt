package com.montdeska.tmdb.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.montdeska.tmdb.BuildConfig
import com.montdeska.tmdb.R
import com.montdeska.tmdb.ui.data.models.Movie
import kotlinx.coroutines.Dispatchers

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MoviesFragment : Fragment(), MoviesContract.View {

    private lateinit var presenter: MoviesPresenter

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer " + BuildConfig.TOKEN
        presenter = MoviesPresenter(this, MoviesModel(headers), Dispatchers.IO)
        presenter.getPopular()
    }

    override fun showPopular(movies: List<Movie>) {
    }

    override fun showUpcoming(movies: List<Movie>) {
    }

    override fun showLatest(movie: Movie) {
    }

    override fun showError(message: String?) {
    }
}