package com.thebest.helloviewmodel.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.thebest.helloviewmodel.model.Post

class PostDetailViewModel : ViewModel() {
    var post: MutableLiveData<Post> = MutableLiveData()
}