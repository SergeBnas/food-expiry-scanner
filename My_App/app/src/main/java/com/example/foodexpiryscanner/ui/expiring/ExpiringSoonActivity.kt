package com.example.foodexpiryscanner.ui.expiring

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodexpiryscanner.R
import com.example.foodexpiryscanner.databinding.ActivityExpiringSoonBinding
import com.example.foodexpiryscanner.ui.home.InventoryAdapter
import com.example.foodexpiryscanner.ui.home.MainActivity
import com.example.foodexpiryscanner.ui.insights.InsightsActivity
import com.example.foodexpiryscanner.ui.library.LibraryActivity
import com.example.foodexpiryscanner.ui.meals.MealSuggestionsActivity
import com.example.foodexpiryscanner.utils.DateUtils
import com.example.foodexpiryscanner.viewmodel.InventoryViewModel

class ExpiringSoonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpiringSoonBinding
    private val viewModel: InventoryViewModel by viewModels()
    private lateinit var adapter: InventoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpiringSoonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = InventoryAdapter { viewModel.deleteItem(it) }
        binding.recyclerViewExpiring.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewExpiring.adapter = adapter

        viewModel.allItems.observe(this) { items ->
            val expiring = items.filter {
                val days = DateUtils.daysUntil(it.expiryDate)
                days != null && days in 0..3
            }
            adapter.submitList(expiring)
            binding.tvEmptyExpiring.visibility = if (expiring.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_expiring
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> { startActivity(Intent(this, MainActivity::class.java)); finish(); true }
                R.id.nav_library -> { startActivity(Intent(this, LibraryActivity::class.java)); finish(); true }
                R.id.nav_expiring -> true
                R.id.nav_insights -> { startActivity(Intent(this, InsightsActivity::class.java)); finish(); true }
                R.id.nav_meals -> { startActivity(Intent(this, MealSuggestionsActivity::class.java)); finish(); true }
                else -> false
            }
        }
    }
}
