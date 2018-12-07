package com.thebest.helloviewmodel.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thebest.helloviewmodel.R
import com.thebest.helloviewmodel.databinding.ItemPostBinding
import com.thebest.helloviewmodel.model.Post
import com.thebest.helloviewmodel.viewmodel.PostViewModel


class PostAdapter(viewModel: PostViewModel,
                  lifecycleOwner: LifecycleOwner,
                  private val listener: PostSelectedListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    init {
        viewModel.posts.observe(lifecycleOwner , Observer{
            if(it != null) {
                posts.addAll(it)
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }

    /**
     * The list of posts of the adapter
     */
    private var posts: ArrayList<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPostBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_post, parent, false)
        return PostViewHolder(binding , listener)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun getItemId(position: Int): Long {
        return posts[position].id.toLong()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class PostViewHolder(private val binding: ItemPostBinding , private val listener: PostSelectedListener) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(post: Post) {
            binding.post = post
            binding.root.setOnClickListener {
                listener.onPostSelected(post)
            }
            binding.executePendingBindings()
        }
    }
}