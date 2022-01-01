package com.spotlightapps.mydog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spotlightapps.mydog.R
import com.spotlightapps.mydog.databinding.DogImageItemBinding

/**
 * Created by Ahmad Jawid Muhammadi
 * on 05-12-2021.
 */

class DogImageAdapter : ListAdapter<String, DogImageViewHolder>(DogImageDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        return DogImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DogImageViewHolder private constructor(
    private val binding: DogImageItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): DogImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return DogImageViewHolder(
                DogImageItemBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

    fun bind(url: String) {
        Glide.with(binding.imageView.context)
            .load(url)
            .placeholder(R.drawable.progress_animation)
            .into(binding.imageView)
    }
}

private object DogImageDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) =
        oldItem == newItem
}