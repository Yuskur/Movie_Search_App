package com.example.movie_search_app.model

import com.google.gson.annotations.SerializedName

data class OMDbMovie(
    @SerializedName("Title") val title : String,
    @SerializedName("Poster") val image : String,
    @SerializedName("Year") val yearOfRelease : String,
    @SerializedName("Type") val genre: String,
    @SerializedName("imdbID") val imdbID: String
)