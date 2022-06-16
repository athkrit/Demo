package com.test.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.demo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val numberAdapter by lazy {
        NumberAdapter(items)
    }

    private val items = mutableListOf<NumberAdapter.Item>()

    private val maxCount = 10

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

            if (selectedIndex == items.lastIndex) {
                items[selectedIndex] = items[selectedIndex].copy(
                    isSelected = false
                )
                items[0] = items[0].copy(
                    isSelected = true
                )

                numberAdapter.notifyItemChanged(selectedIndex)
                numberAdapter.notifyItemChanged(0)
            } else {
                val currentNumber = items[selectedIndex].number
                val nextNumber = items[selectedIndex + 1].number

                when {
                    currentNumber > nextNumber -> {
                        Collections.swap(items, selectedIndex, selectedIndex + 1)
                        numberAdapter.notifyItemMoved(selectedIndex, selectedIndex+1)
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
                numberAdapter.notifyItemRangeChanged(selectedIndex, 2)
            }
        }
    }
}