package com.example.foodexpiryscanner.ui.insights

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexpiryscanner.R
import com.example.foodexpiryscanner.databinding.ActivityInsightsBinding
import com.example.foodexpiryscanner.ui.expiring.ExpiringSoonActivity
import com.example.foodexpiryscanner.ui.home.MainActivity
import com.example.foodexpiryscanner.ui.library.LibraryActivity
import com.example.foodexpiryscanner.ui.meals.MealSuggestionsActivity
import com.example.foodexpiryscanner.utils.DateUtils
import com.example.foodexpiryscanner.viewmodel.InventoryViewModel

class InsightsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsightsBinding
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsightsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.allItems.observe(this) { items ->
            val totalQuantity = items.sumOf { it.quantity }
            val topCategory = items.groupBy { it.category.ifBlank { "Unknown" } }.maxByOrNull { it.value.size }?.key ?: "None yet"
            val expired = items.count { (DateUtils.daysUntil(it.expiryDate) ?: 1) < 0 }
            val expiringSoon = items.count { (DateUtils.daysUntil(it.expiryDate) ?: 100) in 0..3 }

            binding.tvTotalItems.text = getString(R.string.total_items_format, items.size)
            binding.tvTotalQuantity.text = getString(R.string.total_quantity_units_format, totalQuantity)
            binding.tvTopCategory.text = topCategory
            binding.tvExpiredCount.text = expired.toString()
            binding.tvExpiringSoonCount.text = expiringSoon.toString()
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_insights
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> { startActivity(Intent(this, MainActivity::class.java)); finish(); true }
                R.id.nav_library -> { startActivity(Intent(this, LibraryActivity::class.java)); finish(); true }
                R.id.nav_expiring -> { startActivity(Intent(this, ExpiringSoonActivity::class.java)); finish(); true }
                R.id.nav_insights -> true
                R.id.nav_meals -> { startActivity(Intent(this, MealSuggestionsActivity::class.java)); finish(); true }
                else -> false
            }
        }
    }
}
