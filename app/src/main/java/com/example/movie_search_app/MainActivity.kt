package com.example.movie_search_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_search_app.databinding.ActivityMainBinding
import com.example.movie_search_app.model.OMDbMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.omdbapi.com/"
private const val API_KEY = "c9151775"
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        binding.lifecycleOwner = this
        binding.searchPage = viewModel

        //first making the recycler view
        val adapter = MoviesAdapter(this)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        /*
        - making a network request to the yelp api using retrofit
        - BASE_URL is the base endpoint of yelps api
         */
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(OMDbService::class.java)

        viewModel.searchBtn.observe(this, Observer {
            if(it){
                Log.d("searchBtn", "${viewModel.movieSearch}")
                service.searchMovies(API_KEY, viewModel.movieSearch).enqueue(object :
                    Callback<OMDbMovies> {
                    override fun onResponse(
                        call: Call<OMDbMovies>,
                        response: Response<OMDbMovies>
                    ) {
                        Log.d("MainActivity", "onResponse: ${response.code()}")
                        val body = response.body()
                        //if its null then just return
                        if (body == null) {
                            Log.w("MainActivity", "Did not receive valid response body from Yelp API... exiting")
                            return
                        }

                        Log.d("searchBtn", "${body.search[0]}")
                        adapter.submitList(body.search)
                    }

                    override fun onFailure(call: Call<OMDbMovies>, t: Throwable) {
                        Log.i("MainActivity", "onFailure $t")
                    }
                })
                viewModel.setBack()
            }
        })
    }
}