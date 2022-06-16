package com.test.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.demo.databinding.ViewNumberBinding

class NumberAdapter: ListAdapter<NumberAdapter.Item, NumberViewHolder>(diff) {

    data class Item(
        val id: Int,
        val number: Int,
        val isSelected: Boolean
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(ViewNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val item = getItem(position)
        holder.init(item.number, item.isSelected)
    }
}

val diff = object: DiffUtil.ItemCallback<NumberAdapter.Item>() {
    override fun areItemsTheSame(
        oldItem: NumberAdapter.Item,
        newItem: NumberAdapter.Item
    ): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(
        oldItem: NumberAdapter.Item,
        newItem: NumberAdapter.Item
    ): Boolean {
        return newItem == oldItem
    }

}