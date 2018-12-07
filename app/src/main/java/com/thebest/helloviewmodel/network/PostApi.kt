package com.thebest.helloviewmodel.network

import com.thebest.helloviewmodel.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/posts/{id}")
    fun getPostDetail(@Path("id") id: Int ): Observable<Post>
}