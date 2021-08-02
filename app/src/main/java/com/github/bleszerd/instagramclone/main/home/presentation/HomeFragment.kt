package com.github.bleszerd.instagramclone.main.home.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.FragmentMainHomeBinding
import com.github.bleszerd.instagramclone.databinding.FragmentMainProfileBinding

/**
InstagramClone
30/07/2021 - 18:58
Created by bleszerd.
@author alive2k@programmer.net
 */
class HomeFragment() : Fragment() {
    lateinit var binding: FragmentMainHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // TODO: 02/08/2021 app:layout_scrollFlags="scroll" at toolbar
        val binding = FragmentMainHomeBinding.inflate(layoutInflater)

        val recycler: RecyclerView = binding.fragmentMainSearchRecyclerViewSearch
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = PostAdapter()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    inner class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
        private val images = mutableListOf(
            R.drawable.sample_dog,
            R.drawable.banner_sample,
            R.drawable.sample_dog,
            R.drawable.banner_sample,
            R.drawable.sample_dog,
            R.drawable.banner_sample,
            R.drawable.sample_dog,
            R.drawable.banner_sample,
            R.drawable.sample_dog,
            R.drawable.banner_sample,
            R.drawable.sample_dog,
            R.drawable.banner_sample,
        )

        inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(imageId: Int) {
                val postImage =
                    itemView.findViewById<ImageView>(R.id.itemPostListImageViewImagePostList)

                postImage.setImageResource(imageId)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            return PostViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post_list, parent, false))
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(images[position])
        }

        override fun getItemCount(): Int {
            return images.size
        }

    }
}