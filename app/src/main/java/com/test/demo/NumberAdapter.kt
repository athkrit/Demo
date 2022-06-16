package com.test.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.demo.databinding.ViewNumberBinding

class NumberAdapter(private val items: List<Item>): RecyclerView.Adapter<NumberViewHolder>() {

    data class Item(
        val id: Int,
        val number: Int,
        val isSelected: Boolean
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(ViewNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val item = items[position]
        holder.init(item.number, item.isSelected)
    }

    override fun getItemCount() = items.size
}