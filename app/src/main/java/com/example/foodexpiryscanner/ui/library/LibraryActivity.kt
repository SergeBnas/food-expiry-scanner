package com.example.foodexpiryscanner.ui.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodexpiryscanner.R
import com.example.foodexpiryscanner.databinding.ActivityLibraryBinding
import com.example.foodexpiryscanner.ui.expiring.ExpiringSoonActivity
import com.example.foodexpiryscanner.ui.home.InventoryAdapter
import com.example.foodexpiryscanner.ui.home.MainActivity
import com.example.foodexpiryscanner.ui.insights.InsightsActivity
import com.example.foodexpiryscanner.ui.meals.MealSuggestionsActivity
import com.example.foodexpiryscanner.viewmodel.InventoryViewModel

class LibraryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLibraryBinding
    private val viewModel: InventoryViewModel by viewModels()
    private lateinit var adapter: InventoryAdapter
    private var currentQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = InventoryAdapter { viewModel.deleteItem(it) }
        binding.recyclerViewLibrary.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewLibrary.adapter = adapter

        binding.etSearch.doAfterTextChanged {
            currentQuery = it?.toString().orEmpty().trim().lowercase()
            updateList()
        }

        viewModel.allItems.observe(this) {
            updateList()
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_library
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> { startActivity(Intent(this, MainActivity::class.java)); finish(); true }
                R.id.nav_library -> true
                R.id.nav_expiring -> { startActivity(Intent(this, ExpiringSoonActivity::class.java)); finish(); true }
                R.id.nav_insights -> { startActivity(Intent(this, InsightsActivity::class.java)); finish(); true }
                R.id.nav_meals -> { startActivity(Intent(this, MealSuggestionsActivity::class.java)); finish(); true }
                else -> false
            }
        }
    }

    private fun updateList() {
        val items = viewModel.allItems.value.orEmpty()
            .distinctBy { listOf(it.remoteId, it.barcode, it.name, it.expiryDate).joinToString("|") }
        val filtered = if (currentQuery.isBlank()) {
            items
        } else {
            items.filter {
                it.name.contains(currentQuery, ignoreCase = true) ||
                    it.brand.contains(currentQuery, ignoreCase = true) ||
                    it.category.contains(currentQuery, ignoreCase = true)
            }
        }
        adapter.submitList(filtered)
        binding.tvLibraryCount.text = filtered.size.toString()
        binding.tvEmptyLibrary.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
    }
}
