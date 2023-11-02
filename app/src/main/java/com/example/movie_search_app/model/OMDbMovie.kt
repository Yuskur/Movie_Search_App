package com.example.movie_search_app.model

import com.google.gson.annotations.SerializedName

data class OMDbMovie(
    @SerializedName("Title") val title : String,
    @SerializedName("Poster") val image : String,
    @SerializedName("Year") val yearOfRelease : String,
    @SerializedName("Ratings") val ratings : List<Rating>,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    val imdbRating: String
)