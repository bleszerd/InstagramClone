package com.github.bleszerd.instagramclone.main.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.databinding.FragmentMainProfileBinding

/**
InstagramClone
30/07/2021 - 18:58
Created by bleszerd.
@author alive2k@programmer.net
 */
class ProfileFragment() : Fragment() {
    lateinit var binding: FragmentMainProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // TODO: 02/08/2021 app:layout_scrollFlags="scroll" at toolbar 
        val binding = FragmentMainProfileBinding.inflate(layoutInflater)

        val recycler: RecyclerView = binding.fragmentMainProfileRecyclerViewPostList
        recycler.layoutManager = GridLayoutManager(context, 3)
        recycler.adapter = PostAdapter()

        return binding.root
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
                    itemView.findViewById<ImageView>(R.id.itemProfileGridImageViewPhoto)

                postImage.setImageResource(imageId)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            return PostViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_grid, parent, false))
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(images[position])
        }

        override fun getItemCount(): Int {
            return images.size
        }

    }
}