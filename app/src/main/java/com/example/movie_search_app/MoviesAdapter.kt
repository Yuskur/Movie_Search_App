package com.example.movie_search_app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movie_search_app.databinding.MovieBinding
import com.example.movie_search_app.model.OMDbMovie
import com.example.movie_search_app.model.OMDbMovies

class MoviesAdapter(val context: Context) :
    ListAdapter<OMDbMovie, MoviesAdapter.MovieViewHolder>(MovieDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder = MovieViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, context)
    }

    class MovieViewHolder(val binding: MovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
        fun bind(movie : OMDbMovie, context: Context){
            binding.title.text = movie.title
            binding.Year.text = movie.yearOfRelease
            binding.ratingInternet.text = movie.ratings[0].rating
            binding.ratingRottenTomNum.text = movie.ratings[1].rating
            binding.ratingMetacriticNum.text = movie.ratings[2].rating
            binding.runtime.text = movie.runtime
            binding.genre.text = movie.genre
            //binding the image
            Glide.with(context).load(movie.image)
                .apply(
                    RequestOptions().transform(
                        CenterCrop(), RoundedCorners(20)
                    )
                )
                .into(binding.posterImage)
        }
    }

}