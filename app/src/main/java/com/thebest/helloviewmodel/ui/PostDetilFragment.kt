package com.thebest.helloviewmodel.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thebest.helloviewmodel.R
import com.thebest.helloviewmodel.databinding.FragmentPostDetailBinding
import com.thebest.helloviewmodel.viewmodel.PostDetailViewModel

class PostDetilFragment : Fragment() {
    companion object {
        fun newInstance() : PostDetilFragment {
            val args = Bundle()
            val fragment = PostDetilFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentPostDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_post_detail , container , false)
        return binding.root
    }

    private lateinit var postDetailViewModel: PostDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).apply {
            postDetailViewModel = ViewModelProviders.of(this).get(PostDetailViewModel::class.java)
            postDetailViewModel.onViewStateRestored(savedInstanceState)
            postDetailViewModel.post?.observe(this , Observer{
                it?.let {
                    binding.post = it
                }
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        postDetailViewModel.onSaveInstanceState(outState)
    }
}