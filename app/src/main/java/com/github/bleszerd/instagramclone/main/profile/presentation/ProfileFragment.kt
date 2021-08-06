package com.github.bleszerd.instagramclone.main.profile.presentation

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.bleszerd.instagramclone.R
import com.github.bleszerd.instagramclone.common.models.Post
import com.github.bleszerd.instagramclone.common.view.AbstractFragment
import com.github.bleszerd.instagramclone.databinding.FragmentMainProfileBinding
import com.github.bleszerd.instagramclone.main.presentation.MainView

/**
InstagramClone
30/07/2021 - 18:58
Created by bleszerd.
@author alive2k@programmer.net
 */
class ProfileFragment() : AbstractFragment<ProfilePresenter>(), MainView.ProfileView {
    lateinit var binding: FragmentMainProfileBinding

    private lateinit var mainView: MainView
    private lateinit var postAdapter: PostAdapter

    companion object {
        fun newInstance(mainView: MainView, profilePresenter: ProfilePresenter): ProfileFragment {
            val profileFragment = ProfileFragment()

            profileFragment.presenter = profilePresenter
            profileFragment.setMainView(mainView)
            profilePresenter.setView(profileFragment)

            return profileFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        presenter?.findUsers()
    }

    override fun showProgressBar() {
        mainView.showProgressBar()
    }

    override fun hideProgressBar() {
        mainView.hideProgressBar()
    }

    private fun setMainView(mainView: MainView) {
        this.mainView = mainView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // TODO: 02/08/2021 app:layout_scrollFlags="scroll" at toolbar
        binding = FragmentMainProfileBinding.inflate(layoutInflater)

        postAdapter = PostAdapter()
        val recycler: RecyclerView = binding.fragmentMainProfileRecyclerViewPostList
        recycler.layoutManager = GridLayoutManager(context, 3)
        recycler.adapter = postAdapter

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun showPhoto(photo: Uri) {
        try {
            if (context.contentResolver != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, photo)
                binding.fragmentMainProfileCircleImageProfilePhoto.setImageBitmap(bitmap)
            }
        } catch (e: Error) {
            println(e.message)
        }

    }

    override fun showData(name: String, following: String, followers: String, posts: String) {
        binding.fragmentMainProfileTextViewUsername.text = name
        binding.fragmentMainProfileTextViewFollowersLabelCount.text = followers
        binding.fragmentMainProfileTextViewFollowingLabelCount.text = following
        binding.fragmentMainProfileTextViewPostsLabelCount.text = posts
    }

    override fun showPosts(posts: MutableList<Post>) {
        postAdapter.posts = posts
        postAdapter.notifyDataSetChanged()
    }

    inner class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
        var posts = mutableListOf<Post>()

        inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(post: Post) {
                val postImage =
                    itemView.findViewById<ImageView>(R.id.itemProfileGridImageViewPhoto)

                postImage.setImageURI(post.uri)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            return PostViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_grid, parent, false))
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(posts[position])
        }

        override fun getItemCount(): Int {
            return posts.size
        }
    }
}