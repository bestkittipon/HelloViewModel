package com.thebest.helloviewmodel.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thebest.helloviewmodel.R
import com.thebest.helloviewmodel.databinding.FragmentPostBinding
import com.thebest.helloviewmodel.model.Post
import com.thebest.helloviewmodel.viewmodel.PostDetailViewModel
import com.thebest.helloviewmodel.viewmodel.PostViewModel

class PostFragment : Fragment() , PostSelectedListener {


    companion object {
        fun newInstance() : PostFragment {
            val args = Bundle()
            val fragment = PostFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentPostBinding
    private lateinit var postsAdapter: PostAdapter
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_post , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        postsAdapter = PostAdapter(viewModel , this , this)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(context)
        binding.dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)

        observeView()
    }

    override fun onPostSelected(post: Post) {
        (activity as MainActivity).apply {
            val postDetailViewModel = ViewModelProviders.of(this).get(PostDetailViewModel::class.java)
            postDetailViewModel.post.postValue(post)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content_container, PostDetilFragment.newInstance())
                addToBackStack(null)
            }.commit()
        }

    }

    fun observeView() {
        viewModel.posts.observe(this , Observer {
            if(it != null) {
                binding.rvPostList.visibility = View.VISIBLE
            }
            binding.pbLoading.visibility = View.GONE
        })

        viewModel.postLoadError.observe(this , Observer {
            if(!it.isNullOrBlank()) {
                binding.txtMessage.text = it
                binding.txtMessage.visibility = View.VISIBLE
                binding.rvPostList.visibility = View.GONE
            }else {
                binding.txtMessage.text = ""
                binding.txtMessage.visibility = View.GONE
            }
            binding.pbLoading.visibility = View.GONE
        })

        viewModel.loading.observe(this , Observer {loading ->
            if(loading != null) {
                binding.pbLoading.visibility = if (loading) View.VISIBLE else View.GONE
                if (loading) {
                    binding.txtMessage.visibility = View.GONE
                    binding.rvPostList.visibility = View.GONE
                }
            }
        })
    }
}