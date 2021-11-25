package com.joesemper.redditpopularposts.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.entity.PostInfo
import com.joesemper.redditpopularposts.databinding.ItemPostBinding

class PostsRvAdapter : PagingDataAdapter<Children, PostsViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position)?.data)
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<Children>() {
    override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem.data.id == newItem.data.id
    }

    override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem == newItem
    }
}

class PostsViewHolder(
    val binding: ItemPostBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PostInfo?) {
        item?.let {
            with(binding) {
                tvTitle.text = item.title
                tvComments.text = item.num_comments.toString()
                tvStars.text = item.total_awards_received.toString()
            }
        }
    }
}