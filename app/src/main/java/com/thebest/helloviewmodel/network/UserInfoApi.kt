package com.thebest.helloviewmodel.network

import com.thebest.helloviewmodel.model.UserInfoDao
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoApi {
    @GET("/users/t-jedsada")
    fun getUserInfoGitHub(): Observable<UserInfoDao>
}