package com.example.movie_search_app

import com.example.movie_search_app.model.OMDbMovie
import com.example.movie_search_app.model.OMDbMovies
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

interface OMDbService{

    fun searchMovies(
        @Header("Authorization") apiKey: String,
        @Query("s") movieName: String
    ) : Call<OMDbMovies>

}