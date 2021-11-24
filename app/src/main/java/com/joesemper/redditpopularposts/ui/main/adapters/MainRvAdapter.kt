package com.joesemper.redditpopularposts.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.entity.PostInfo
import com.joesemper.redditpopularposts.databinding.ItemPostBinding

class MainRvAdapter(
    val data: List<Children>
) :
    RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostInfo) {
            with(binding) {
                tvTitle.text = item.title
                tvComments.text = item.num_comments.toString()
                tvStars.text = item.total_awards_received.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(data[position].data)
    }

    override fun getItemCount() = data.size
}