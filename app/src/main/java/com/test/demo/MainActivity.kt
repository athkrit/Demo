package com.test.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.demo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val numberAdapter by lazy {
        NumberAdapter()
    }

    private val items = mutableListOf<NumberAdapter.Item>()
    private val maxCount = 10
    private var isSwapped = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items.clear()
        for (i in 0..maxCount) {
            items.add(
                NumberAdapter.Item(
                    id = i,
                    number = (0..10).random(),
                    isSelected = i == 0
                )
            )
        }

        numberAdapter.submitList(items.toList())

        with (binding.rvNumber) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = numberAdapter
        }

        binding.bNext.setOnClickListener {
            val selectedIndex = items.indexOfFirst { it.isSelected }

            if (selectedIndex == -1) {
                binding.bNext.isEnabled = false
                return@setOnClickListener
            }

            if (selectedIndex == 0) {
                isSwapped = false
            }

            if (selectedIndex == items.lastIndex) {
                if (!isSwapped) {
                    items[selectedIndex] = items[selectedIndex].copy(
                        isSelected = false
                    )
                    items[0] = items[0].copy(
                        isSelected = false
                    )
                } else {
                    items[selectedIndex] = items[selectedIndex].copy(
                        isSelected = false
                    )
                    items[0] = items[0].copy(
                        isSelected = true
                    )
                }

            } else {
                val currentNumber = items[selectedIndex].number
                val nextNumber = items[selectedIndex + 1].number

                when {
                    currentNumber > nextNumber -> {
                        Collections.swap(items, selectedIndex, selectedIndex + 1)
                        isSwapped = true
                    }
                    else -> {
                        items[selectedIndex] = items[selectedIndex].copy(
                            isSelected = false
                        )
                        items[selectedIndex + 1] = items[selectedIndex + 1].copy(
                            isSelected = true
                        )
                    }
                }
            }

            updateAdapter()
        }
    }

    private fun updateAdapter() {
        numberAdapter.submitList(items.toList())
    }
}