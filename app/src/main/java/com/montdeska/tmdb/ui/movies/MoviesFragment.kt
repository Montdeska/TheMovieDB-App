package com.montdeska.tmdb.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.montdeska.tmdb.BuildConfig
import com.montdeska.tmdb.R
import com.montdeska.tmdb.data.models.Movie
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.Dispatchers
import kotlin.math.abs

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MoviesFragment : Fragment(), MoviesContract.View {

    private lateinit var presenter: MoviesPresenter

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: MoviesAdapter

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
        adapter = MoviesAdapter(movieClickListener())
//        viewPager.registerOnPageChangeCallback(pageListener);
        configViewPager()
        addPageTransformer()

        presenter = MoviesPresenter(this, MoviesModel(headers), Dispatchers.IO)
        presenter.getPopular()
        popularTextView.setOnClickListener {
            presenter.getPopular()
            popularTextView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.open_sans_bold)
            latestTextView.typeface = ResourcesCompat.getFont(requireContext(), R.font.open_sans)
            upcomingTextView.typeface = ResourcesCompat.getFont(requireContext(), R.font.open_sans)
        }
        latestTextView.setOnClickListener {
            presenter.getLatest()
            popularTextView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.open_sans)
            latestTextView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.open_sans_bold)
            upcomingTextView.typeface = ResourcesCompat.getFont(requireContext(), R.font.open_sans)
        }
        upcomingTextView.setOnClickListener {
            presenter.getUpcoming()
            popularTextView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.open_sans)
            latestTextView.typeface = ResourcesCompat.getFont(requireContext(), R.font.open_sans)
            upcomingTextView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.open_sans_bold)
        }

    }

    private fun addPageTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(10))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = .85f + r * .15f
        }
        viewPager.setPageTransformer(transformer)
    }

//    private val pageListener = object : ViewPager2.OnPageChangeCallback() {
//        override fun onPageSelected(position: Int) {
//            super.onPageSelected(position)
//            val selectedMovieId = adapter.getItemMovie(position).id
//            viewPager.findViewWithTag<RoundedImageView>(selectedMovieId).alpha = 1f
//        }
//    }

    private fun configViewPager() {
        viewPager.adapter = adapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun movieClickListener(): MoviesAdapter.MovieClickListener {
        return MoviesAdapter.MovieClickListener { movieId ->
//            val bundle = Bundle()
//            bundle.putString("storyUrl", storyUrl)
//            NavHostFragment.findNavController(this).navigate(R.id.storyReaderFragment, bundle)
        }
    }

    override fun showPopular(movies: List<Movie>) {
        adapter.submitList(movies)
    }

    override fun showUpcoming(movies: List<Movie>) {
        adapter.submitList(movies)
    }

    override fun showLatest(movie: Movie) {
        val list = ArrayList<Movie>()
        list.add(movie)
        adapter.submitList(list)

    }

    override fun showError(message: String?) {
    }
}