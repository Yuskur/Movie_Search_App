package com.example.movie_search_app.model

import com.google.gson.annotations.SerializedName

data class OMDbMovies(
    val totalResults: String,
    @SerializedName("Search") val search: List<OMDbMovie>
)