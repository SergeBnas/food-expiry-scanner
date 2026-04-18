package com.example.foodexpiryscanner.ui.meals

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.foodexpiryscanner.R
import com.example.foodexpiryscanner.databinding.ActivityMealSuggestionsBinding
import com.example.foodexpiryscanner.ui.expiring.ExpiringSoonActivity
import com.example.foodexpiryscanner.ui.home.MainActivity
import com.example.foodexpiryscanner.ui.insights.InsightsActivity
import com.example.foodexpiryscanner.ui.library.LibraryActivity
import com.example.foodexpiryscanner.utils.DateUtils
import com.example.foodexpiryscanner.viewmodel.InventoryViewModel

class MealSuggestionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealSuggestionsBinding
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealSuggestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.allItems.observe(this) { items ->
            val soon = items
                .filter { (DateUtils.daysUntil(it.expiryDate) ?: 100) in 0..3 }
                .map { it.name.lowercase() }

            val suggestions = linkedSetOf<String>()

            if (soon.any { it.contains("egg") } && soon.any { it.contains("milk") || it.contains("cheese") }) {
                suggestions += "• Creamy omelette with eggs, cheese, milk, and any vegetables you have"
            }
            if (soon.any { it.contains("bread") } && soon.any { it.contains("cheese") || it.contains("ham") || it.contains("turkey") }) {
                suggestions += "• Toasted sandwiches using bread, cheese, and deli fillings"
            }
            if (soon.any { it.contains("tomato") } || soon.any { it.contains("pasta") }) {
                suggestions += "• Quick pasta with tomatoes, garlic, onion, and herbs"
            }
            if (soon.any { it.contains("yogurt") } || soon.any { it.contains("banana") } || soon.any { it.contains("berry") }) {
                suggestions += "• Breakfast smoothie bowl with yogurt and ripe fruit"
            }
            if (soon.any { it.contains("chicken") } || soon.any { it.contains("rice") }) {
                suggestions += "• Chicken rice bowl with vegetables and a light sauce"
            }
            if (soon.any { it.contains("potato") } || soon.any { it.contains("onion") }) {
                suggestions += "• Oven tray bake with potatoes, onions, and pantry spices"
            }
            if (soon.any { it.contains("vegetable") || it.contains("carrot") || it.contains("onion") || it.contains("zucchini") }) {
                suggestions += "• Vegetable soup or stir-fry using the freshest items first"
            }
            if (soon.any { it.contains("tuna") } || soon.any { it.contains("corn") }) {
                suggestions += "• Tuna pasta salad with corn, cucumber, and yogurt dressing"
            }
            if (soon.any { it.contains("apple") } || soon.any { it.contains("pear") }) {
                suggestions += "• Fruit oatmeal or cinnamon baked fruit for a quick snack"
            }

            if (suggestions.isEmpty()) {
                suggestions += listOf(
                    "• Mixed vegetable stir-fry with rice or noodles",
                    "• Pasta using the oldest vegetables and cheese",
                    "• Soup made from items in the Soon tab",
                    "• Wraps with any protein, lettuce, and sauces",
                    "• Rice bowl with eggs, vegetables, and yogurt sauce",
                    "• Toast or flatbread topped with cheese and tomatoes"
                )
            }

            val header = if (soon.isEmpty()) {
                "No items expire in the next 3 days. Here are flexible meal ideas:\n\n"
            } else {
                "Use first: ${soon.distinct().joinToString(", ")}\n\nSuggested meals:\n"
            }

            binding.tvMeals.text = header + suggestions.joinToString("\n\n")
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_meals
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_library -> {
                    startActivity(Intent(this, LibraryActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_expiring -> {
                    startActivity(Intent(this, ExpiringSoonActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_insights -> {
                    startActivity(Intent(this, InsightsActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_meals -> true
                else -> false
            }
        }
    }
}
