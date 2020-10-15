package com.montdeska.tmdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.montdeska.tmdb.ui.data.models.Movie
import com.montdeska.tmdb.ui.data.models.Movies
import com.montdeska.tmdb.ui.movies.MoviesContract
import com.montdeska.tmdb.ui.movies.MoviesFragment
import com.montdeska.tmdb.ui.movies.MoviesModel
import com.montdeska.tmdb.ui.movies.MoviesPresenter
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MoviesPresenterTest {
    private lateinit var view: MoviesContract.View
    private lateinit var model: MoviesContract.Model
    private lateinit var headers: HashMap<String, String>
    private lateinit var presenter: MoviesPresenter

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        headers = HashMap()
        view = Mockito.mock(MoviesFragment::class.java)
        model = Mockito.mock(MoviesModel::class.java)
        presenter = MoviesPresenter(view, model, coroutinesTestRule.testDispatcher)
    }

    @Test
    fun `Test if popular movies are received`() = runBlocking {
        val results = ArrayList<Movie>()
        results.add(Movie(1, "Test title 1"))
        results.add(Movie(2, "Test title 2"))
        results.add(Movie(3, "Test title 3"))
        val movies = Movies(results)

        Mockito.`when`(model.getPopularData()).then { movies }
        presenter.getPopular()
        verify(view).showPopular(movies.results)
    }

    @Test
    fun `Test if popular movies are empty`() = runBlocking {
        val results = ArrayList<Movie>()
        val movies = Movies(results)

        Mockito.`when`(model.getPopularData()).then { movies }
        presenter.getPopular()
        verify(view).showError("We couldn't get the popular movies")
    }

    @Test
    fun `Test if upcoming movies are received`() = runBlocking {
        val results = ArrayList<Movie>()
        results.add(Movie(1, "Test title 1"))
        results.add(Movie(2, "Test title 2"))
        results.add(Movie(3, "Test title 3"))
        val movies = Movies(results)

        Mockito.`when`(model.getUpcomingData()).then { movies }
        presenter.getUpcoming()
        verify(view).showUpcoming(movies.results)
    }

    @Test
    fun `Test if latest is received`() = runBlocking {
        val movie = Movie(1, "Test title 1")
        Mockito.`when`(model.getLatestData()).then { movie }
        presenter.getLatest()
        verify(view).showLatest(movie)
    }
}