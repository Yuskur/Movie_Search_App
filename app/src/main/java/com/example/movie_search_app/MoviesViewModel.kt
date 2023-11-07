package com.example.movie_search_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    var movieSearch = ""
    var id = ""

    //Live data for the search button, link clicked, and the share button
    private var _searchBtn = MutableLiveData(false)
    val searchBtn : LiveData<Boolean>
        get() = _searchBtn
    private var _clickedLink = MutableLiveData(false)
    val clickedLink : LiveData<Boolean>
        get() = _clickedLink
    private var _shareClicked = MutableLiveData(false)
    val shareClicked : LiveData<Boolean>
        get() = _shareClicked

    //function for when something is typed and search is pressed
    fun search(){
        if(movieSearch != ""){
            _searchBtn.value = true
        }
    }
    //when the IMDb link on the poster is clicked
    fun linkClicked(id: String){
        this.id = id
        _clickedLink.value = true
    }

    //when the share button is clicked on the poster
    fun shareClicked(id: String){
        this.id = id
        _shareClicked.value = true
    }
    //sets everything back when done
    fun setBack(){
        _searchBtn.value = false
        _clickedLink.value = false
        _shareClicked.value = false
    }

}