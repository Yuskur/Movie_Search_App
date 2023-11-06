package com.example.movie_search_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_search_app.databinding.ActivityMainBinding
import com.example.movie_search_app.model.OMDbMovies
import com.google.android.material.appbar.MaterialToolbar
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

        //setting up the bottom toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.topbar)
        setSupportActionBar(toolbar)

        binding.lifecycleOwner = this
        binding.searchPage = viewModel

        fun linkClicked(id: String) : Unit{
            viewModel.linkClicked(id)
        }

        fun shareClicked(id: String): Unit{
            viewModel.shareClicked(id)
        }

        //first making the recycler view
        val adapter = MoviesAdapter(this,::linkClicked,::shareClicked)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.clickedLink.observe(this, Observer{
            if(it){
                Log.d("link", "ID: ${viewModel.id}")
                val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.imdb.com/title/${viewModel.id}/"))
                startActivity(intent)
                viewModel.setBack()
            }
        })

        viewModel.shareClicked.observe(this, Observer {
            if(it){
                Log.d("share", "ID: ${viewModel.id}")
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "https://www.imdb.com/title/${viewModel.id}/")
                startActivity(Intent.createChooser(intent, "Share Link: "))
                viewModel.setBack()
            }
        })

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
                service.searchMovies(API_KEY, viewModel.movieSearch, "movie").enqueue(object :
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

//                        Log.d("searchBtn", "${body.search[0]}")
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

    /*When the email button is clicked on the bottom toolbar
it will make out an email for feedback the developer
 */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d("MainActivity", "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.bottom_bar, menu) // Inflate your menu resource here
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.emailBtn -> {
                Log.d("MainActivity", "onOptionsItemSelected")
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("test.dummy2090@gmail.com"))
//                intent.data = Uri.parse("mailto:test.dummy2090@gmail.com")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}