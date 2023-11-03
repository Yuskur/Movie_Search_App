package com.example.movie_search_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    var movieSearch = ""

    private var _searchBtn = MutableLiveData(false)
    val searchBtn : LiveData<Boolean>
        get() = _searchBtn
    fun search(){
        if(movieSearch != ""){
            _searchBtn.value = true
        }
    }
    fun setBack(){
        _searchBtn.value = false
    }
}