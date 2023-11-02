package com.example.movie_search_app

import androidx.recyclerview.widget.DiffUtil
import com.example.movie_search_app.model.OMDbMovie

class MovieDiffItemCallback : DiffUtil.ItemCallback<OMDbMovie>() {
    override fun areItemsTheSame(oldItem: OMDbMovie, newItem: OMDbMovie): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: OMDbMovie, newItem: OMDbMovie): Boolean {
        return oldItem == newItem
    }
}