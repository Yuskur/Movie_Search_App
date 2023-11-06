package com.example.movie_search_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    var movieSearch = ""
    var id = ""

    private var _searchBtn = MutableLiveData(false)
    val searchBtn : LiveData<Boolean>
        get() = _searchBtn
    private var _clickedLink = MutableLiveData(false)
    val clickedLink : LiveData<Boolean>
        get() = _clickedLink
    private var _shareClicked = MutableLiveData(false)
    val shareClicked : LiveData<Boolean>
        get() = _shareClicked
    fun search(){
        if(movieSearch != ""){
            _searchBtn.value = true
        }
    }
    fun linkClicked(id: String){
        this.id = id
        _clickedLink.value = true
    }
    fun shareClicked(id: String){
        this.id = id
        _shareClicked.value = true
    }
    fun setBack(){
        _searchBtn.value = false
        _clickedLink.value = false
        _shareClicked.value = false
    }

}