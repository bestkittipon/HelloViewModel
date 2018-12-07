package com.thebest.helloviewmodel.model

import com.google.gson.annotations.SerializedName

data class UserInfoDao(@SerializedName("login") var login: String,
                       @SerializedName("id") var id: Long,
                       @SerializedName("avatar_url") var avatarUrl: String,
                       @SerializedName("url") var url: String,
                       @SerializedName("type") var type: String,
                       @SerializedName("name") var name: String,
                       @SerializedName("company") var company: String,
                       @SerializedName("blog") var blog: String,
                       @SerializedName("location") var location: String,
                       @SerializedName("email") var email: String,
                       @SerializedName("hireable") var hireable: Boolean,
                       @SerializedName("bio") var bio: String,
                       @SerializedName("followers") var followers: Int,
                       @SerializedName("following") var following: Int)