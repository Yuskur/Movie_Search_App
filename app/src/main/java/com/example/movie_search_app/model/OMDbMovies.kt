package com.example.movie_search_app.model

import com.google.gson.annotations.SerializedName

data class OMDbMovies(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("Search") val search: List<OMDbMovie>
)