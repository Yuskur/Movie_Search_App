package com.example.movie_search_app

import com.example.movie_search_app.model.OMDbMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

public interface OMDbService{

    @GET("search")
    fun searchMovies(
        @Header("apikey") apiKey: String,
        @Query("term") movieName: String
    ) : Call<OMDbMovies>
}