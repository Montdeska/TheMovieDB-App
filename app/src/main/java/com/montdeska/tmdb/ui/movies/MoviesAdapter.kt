package com.montdeska.tmdb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.montdeska.tmdb.databinding.SliderItemBinding
import com.montdeska.tmdb.data.models.Movie


class MoviesAdapter(private val movieClickListener: MovieClickListener) :
    ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

    class MoviesViewHolder private constructor(private val binding: SliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MoviesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SliderItemBinding.inflate(layoutInflater, parent, false)
                return MoviesViewHolder(binding)
            }
        }

        fun bind(movie: Movie, movieClickListener: MovieClickListener) {
            binding.movie = movie
            binding.movieClickListener = movieClickListener
            binding.executePendingBindings()
        }
    }


    class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class MovieClickListener(val movieClickListener: (movieId: Int) -> Unit) {
        fun onClick(item: Movie) {
            movieClickListener(item.id)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position)!!, movieClickListener)
    }

    fun getItemMovie(position: Int): Movie {
        return super.getItem(position)
    }
}
