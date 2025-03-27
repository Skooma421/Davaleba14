package com.example.myapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.davaleba14new.Dessert
import com.example.davaleba14new.databinding.ItemDessertBinding

class DessertAdapter(private val onRemoveClick: (Dessert) -> Unit) :
    ListAdapter<Dessert, DessertAdapter.DessertViewHolder>(DIFF_CALLBACK) {

    class DessertViewHolder(private val binding: ItemDessertBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dessert: Dessert, onRemoveClick: (Dessert) -> Unit) {
            binding.text.text = dessert.name
            binding.image.setImageResource(dessert.imageRes)

            binding.btnRemove.setOnClickListener { onRemoveClick(dessert) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertViewHolder {
        val binding = ItemDessertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DessertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DessertViewHolder, position: Int) {
        holder.bind(getItem(position), onRemoveClick)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Dessert>() {
            override fun areItemsTheSame(oldItem: Dessert, newItem: Dessert): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Dessert, newItem: Dessert): Boolean =
                oldItem == newItem
        }
    }
}
