package com.example.foodexpiryscanner.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.foodexpiryscanner.R
import com.example.foodexpiryscanner.databinding.ActivityMainBinding
import com.example.foodexpiryscanner.ui.addedit.AddEditItemActivity
import com.example.foodexpiryscanner.ui.expiring.ExpiringSoonActivity
import com.example.foodexpiryscanner.ui.insights.InsightsActivity
import com.example.foodexpiryscanner.ui.library.LibraryActivity
import com.example.foodexpiryscanner.ui.meals.MealSuggestionsActivity
import com.example.foodexpiryscanner.ui.scan.ScanActivity
import com.example.foodexpiryscanner.utils.DateUtils
import com.example.foodexpiryscanner.viewmodel.InventoryViewModel
import com.example.foodexpiryscanner.worker.ExpiryReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: InventoryViewModel by viewModels()
    private lateinit var adapter: InventoryAdapter

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        askNotificationPermission()
        scheduleReminders()

        adapter = InventoryAdapter { item -> viewModel.deleteItem(item) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allItems.observe(this) { items ->
            val recentItems = items.distinctBy { listOf(it.remoteId, it.barcode, it.name, it.expiryDate).joinToString("|") }.take(3)
            adapter.submitList(recentItems)
            binding.tvCount.text = items.size.toString()
            val expiringSoonCount = items.count { (DateUtils.daysUntil(it.expiryDate) ?: 100) in 0..3 }
            binding.tvExpiringCount.text = expiringSoonCount.toString()
            binding.tvEmpty.visibility = if (items.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }

        viewModel.syncMessage.observe(this) { message ->
            if (!message.isNullOrBlank()) {
                DateUtils.toast(this, message)
                viewModel.clearSyncMessage()
            }
        }

        binding.btnScan.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
        binding.btnAddManual.setOnClickListener {
            startActivity(Intent(this, AddEditItemActivity::class.java))
        }
        binding.btnSync.setOnClickListener {
            viewModel.syncWithBackend()
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> true
                R.id.nav_library -> {
                    startActivity(Intent(this, LibraryActivity::class.java)); finish(); true
                }
                R.id.nav_expiring -> {
                    startActivity(Intent(this, ExpiringSoonActivity::class.java)); finish(); true
                }
                R.id.nav_insights -> {
                    startActivity(Intent(this, InsightsActivity::class.java)); finish(); true
                }
                R.id.nav_meals -> {
                    startActivity(Intent(this, MealSuggestionsActivity::class.java)); finish(); true
                }
                else -> false
            }
        }
    }

    private fun scheduleReminders() {
        val workRequest = PeriodicWorkRequestBuilder<ExpiryReminderWorker>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "expiry_reminder",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}
