package com.joesemper.redditpopularposts.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.entity.PostInfo
import com.joesemper.redditpopularposts.databinding.ItemPostBinding

class PostsRvAdapter : PagingDataAdapter<PostInfo, PostsViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<PostInfo>() {
    override fun areItemsTheSame(oldItem: PostInfo, newItem: PostInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostInfo, newItem: PostInfo): Boolean {
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