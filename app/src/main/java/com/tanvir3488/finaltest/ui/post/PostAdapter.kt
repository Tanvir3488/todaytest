package com.tanvir3488.finaltest.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tanvir3488.finaltest.data.dto.response.Post
import com.tanvir3488.finaltest.databinding.RowPostBinding

/******

 **** Created By  TANVIR3488 AT 19/8/25 8:49 PM

 ******/


class PostAdapter constructor(val onItemClick: (Post) -> Unit): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {



    val diffUtil = object : DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(
            oldItem: Post,
            newItem: Post
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Post,
            newItem: Post
        ): Boolean {
            return newItem.id == oldItem.id
        }

    }

    private val asyncList = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {

        val binding = RowPostBinding.inflate(LayoutInflater.from(parent.context))

        return PostViewHolder(binding)

    }

    fun submitList(list: List<Post>){
        asyncList.submitList(list)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        return holder.bind(asyncList.currentList[position],onItemClick)
    }

    override fun getItemCount(): Int {
        return asyncList.currentList.size
    }


    inner class PostViewHolder(val binding: RowPostBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Post,onItemClick:(Post)->Unit){
            binding.apply {
                tvTitle.text = item.title
                tvBody.text = item.body
                tvPostId.text = item.id.toString()
                tvUserId.text = item.userId.toString()
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}
