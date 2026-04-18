package com.example.foodexpiryscanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodexpiryscanner.data.local.AppDatabase
import com.example.foodexpiryscanner.data.local.InventoryItemEntity
import com.example.foodexpiryscanner.data.repository.InventoryRepository
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InventoryRepository
    val allItems: LiveData<List<InventoryItemEntity>>
    private val _syncMessage = MutableLiveData<String>()
    val syncMessage: LiveData<String> = _syncMessage

    init {
        val inventoryDao = AppDatabase.getDatabase(application).inventoryDao()
        repository = InventoryRepository(inventoryDao)
        allItems = repository.allItems
    }

    fun insertItem(item: InventoryItemEntity) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun updateItem(item: InventoryItemEntity) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: InventoryItemEntity) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun clearSyncMessage() {
        _syncMessage.value = null
    }

    fun syncWithBackend() {
        viewModelScope.launch {
            try {
                val syncedCount = repository.syncPendingItems()
                val pulledCount = repository.pullRemoteItems()
                _syncMessage.value = "Sync complete: $syncedCount uploaded, $pulledCount refreshed"
            } catch (e: Exception) {
                _syncMessage.value = "Sync failed. Start backend server first."
            }
        }
    }
}
