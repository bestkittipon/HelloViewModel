package com.thebest.helloviewmodel.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.util.Log
import com.thebest.helloviewmodel.model.Post
import com.thebest.helloviewmodel.network.PostApi
import com.thebest.helloviewmodel.utils.BASE_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostDetailViewModel : ViewModel() {
    var post: MutableLiveData<Post>? = MutableLiveData()

    fun onSaveInstanceState(outState: Bundle) {
        post?.value?.let {
            outState.putInt("post_id" , it.id)
        }
    }

    fun onViewStateRestored(savedInstanceState: Bundle?) {
        if(post?.value?.id == null) {
            savedInstanceState?.let {
                if(it.containsKey("post_id")) {
                    loadPostDetail(it.getInt("post_id"))
                }
            }
        }
    }

    fun loadPostDetail(id: Int) {
        providesPostAPIs().getPostDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it != null) {
                        post?.postValue(it)
                    }
                }, {
                   Log.e(javaClass.simpleName , it.message)
                })
    }

    fun providesPostAPIs(): PostApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
}