package com.test.demo

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.test.demo.databinding.ViewNumberBinding

class NumberViewHolder(
    private val binding: ViewNumberBinding
): RecyclerView.ViewHolder(binding.root) {
    fun init(number: Int,
             isSelected: Boolean) {
        binding.tvNumber.text = number.toString()
        binding.tvPointer.isVisible = isSelected
    }
}