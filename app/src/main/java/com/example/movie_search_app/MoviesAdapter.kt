package com.example.movie_search_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
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

class MoviesAdapter(val context: Context, val linkClickedListener: (id: String) -> Unit) :
    ListAdapter<OMDbMovie, MoviesAdapter.MovieViewHolder>(MovieDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder = MovieViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, context, linkClickedListener)
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
        fun bind(movie : OMDbMovie, context: Context, linkClickedListener: (id: String) -> Unit){
            binding.title.text = "Title: ${movie.title}"
            binding.Year.text = "Year: ${movie.yearOfRelease}"
//            binding.ratingInternet.text = movie.ratings[0].rating
//            binding.ratingRottenTomNum.text = movie.ratings[1].rating
//            binding.ratingMetacriticNum.text = movie.ratings[2].rating
            binding.runtime.text = "Movie Runtime: ${movie.runtime}"
            binding.genre.text = "Genre: ${movie.genre}"
            //binding the image
            Glide.with(context).load(movie.image)
                .apply(
                    RequestOptions().transform(
                        CenterCrop(), RoundedCorners(20)
                    )
                )
                .into(binding.posterImage)
            binding.ratingImdb.text = "IMDB Rating: ${movie.imdbRating}"
            binding.link.setOnClickListener{ linkClickedListener(movie.imdbID) }
            binding.link.text = "IMDB page: ${movie.title}"
        }
    }

}