package com.thebest.helloviewmodel.ui

import com.thebest.helloviewmodel.model.Post

interface PostSelectedListener {
    fun onPostSelected(post: Post)
}