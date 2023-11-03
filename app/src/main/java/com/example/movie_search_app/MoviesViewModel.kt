package com.example.movie_search_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    var movieSearch = ""

    private var _searchBtn = MutableLiveData(false)
    val searchBtn : LiveData<Boolean>
        get() = _searchBtn
    private var _clickedLink = MutableLiveData(false)
    val clickedLink : LiveData<Boolean>
        get() = _clickedLink
    fun search(){
        if(movieSearch != ""){
            _searchBtn.value = true
        }
    }
    fun linkClicked(){
        _clickedLink.value = true
    }
    fun setBack(){
        _searchBtn.value = false
        _clickedLink.value = false
    }
}