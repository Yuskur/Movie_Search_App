package com.example.movie_search_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    private var _movieSearch = MutableLiveData("")
    val movieSearch : LiveData<String>
        get() = _movieSearch

    private var _searchBtn = MutableLiveData(false)
    val searchBtn : LiveData<Boolean>
        get() = searchBtn


    fun search(){
        if(_movieSearch.value != ""){
            _searchBtn.value = true
        }
    }
    fun setBack(){
        _searchBtn.value = false
    }
    fun getSearchedMovie(): String{
        return _movieSearch.value!!
    }
}