package com.thebest.helloviewmodel.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.thebest.helloviewmodel.utils.BASE_URL
import com.thebest.helloviewmodel.model.Post
import com.thebest.helloviewmodel.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel : ViewModel() {
    var posts: MutableLiveData<List<Post>> = MutableLiveData()
    var postLoadError: MutableLiveData<String> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        providesPostAPIs().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.isNotEmpty()) {
                        posts.value = it
                    }
                }, {
                    postLoadError.value = it.message.toString()
                })
    }

    fun providesPostAPIs(): PostApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
}