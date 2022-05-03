package com.samsdk.mvvmexample.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samsdk.mvvmexample.databinding.ItemLayoutBinding
import com.samsdk.mvvmexample.model.RecyclerData

class RvAdapter : ListAdapter<RecyclerData, RvAdapter.RvViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<RecyclerData>() {
        override fun areItemsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RvViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecyclerData) {
            binding.apply {
                Glide.with(imageThumb)
                    .load(data.owner.avatar_url)
                    .circleCrop()
                    .into(imageThumb)

                tvTitle.text = data.name
                tvDesc.text =
                    if (TextUtils.isEmpty(data.description)) "No data" else data.description
            }
        }
    }
}