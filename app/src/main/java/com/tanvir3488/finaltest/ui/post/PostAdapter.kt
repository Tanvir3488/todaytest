package com.tanvir3488.finaltest.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanvir3488.finaltest.data.dto.response.Post
import com.tanvir3488.finaltest.databinding.RowPostBinding

/******

 **** Created By  TANVIR3488 AT 19/8/25 8:49 PM

 ******/


class PostAdapter2(val onItemClick: (Post) -> Unit) :
    ListAdapter<Post, PostAdapter2.PostViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = RowPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    inner class PostViewHolder(private val binding: RowPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post, onItemClick: (Post) -> Unit) {
            binding.apply {
                tvTitle.text = item.title
                tvBody.text = item.body
                tvPostId.text = item.id.toString()
                tvUserId.text = item.userId.toString()
            }

            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
}


class PostAdapter(val onItemClick: (Post) -> Unit): ListAdapter<Post, PostAdapter.PostViewHolder>(DIFF_CALLBACK2){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val binding = RowPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        if (position == holder.adapterPosition && position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener{
                onItemClick(getItem(position))
            }
        }

       holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK2 = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class PostViewHolder(private val binding: RowPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post) {
            binding.apply {
                tvTitle.text = item.title
                tvBody.text = item.body
                tvPostId.text = item.id.toString()
                tvUserId.text = item.userId.toString()
            }

        }
    }
}

