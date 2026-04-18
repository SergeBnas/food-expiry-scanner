package com.example.foodexpiryscanner.data.repository

import androidx.lifecycle.LiveData
import com.example.foodexpiryscanner.data.local.InventoryDao
import com.example.foodexpiryscanner.data.local.InventoryItemEntity
import com.example.foodexpiryscanner.data.remote.ApiModule
import com.example.foodexpiryscanner.data.remote.BackendItemDto

class InventoryRepository(private val inventoryDao: InventoryDao) {

    val allItems: LiveData<List<InventoryItemEntity>> = inventoryDao.getAllItems()

    suspend fun insertItem(item: InventoryItemEntity) {
        inventoryDao.insertItem(item)
    }

    suspend fun updateItem(item: InventoryItemEntity) {
        inventoryDao.updateItem(item)
    }

    suspend fun deleteItem(item: InventoryItemEntity) {
        if (item.remoteId.isNotBlank()) {
            runCatching { ApiModule.backendApi.deleteItem(item.remoteId) }
        }
        inventoryDao.deleteItem(item)
    }

    suspend fun getItemById(itemId: Int): InventoryItemEntity? {
        return inventoryDao.getItemById(itemId)
    }

    suspend fun deleteAllItems() {
        inventoryDao.deleteAllItems()
    }

    suspend fun syncPendingItems(): Int {
        val unsyncedItems = inventoryDao.getUnsyncedItems()
        var syncedCount = 0

        unsyncedItems.forEach { item ->
            val dto = BackendItemDto(
                _id = item.remoteId.ifBlank { null },
                barcode = item.barcode,
                name = item.name,
                brand = item.brand,
                category = item.category,
                quantity = item.quantity,
                expiryDate = item.expiryDate,
                imageUrl = item.imageUrl,
                notes = ""
            )
            val response = ApiModule.backendApi.createItem(dto)
            if (response.isSuccessful) {
                val remoteId = response.body()?._id.orEmpty()
                inventoryDao.markItemSynced(item.id, true, remoteId)
                syncedCount++
            }
        }

        return syncedCount
    }

    suspend fun pullRemoteItems(): Int {
        val response = ApiModule.backendApi.getItems()
        if (!response.isSuccessful) return 0

        val remoteItems = response.body().orEmpty()
        var importedCount = 0

        remoteItems.forEach { dto ->
            val remoteId = dto._id.orEmpty()
            val existingByRemote = if (remoteId.isNotBlank()) inventoryDao.getItemByRemoteId(remoteId) else null
            if (existingByRemote != null) return@forEach

            val duplicate = inventoryDao.getDuplicate(dto.barcode, dto.name, dto.expiryDate)
            if (duplicate != null) {
                inventoryDao.updateItem(
                    duplicate.copy(
                        remoteId = remoteId.ifBlank { duplicate.remoteId },
                        brand = dto.brand,
                        category = dto.category,
                        quantity = dto.quantity,
                        imageUrl = dto.imageUrl,
                        isSynced = true
                    )
                )
            } else {
                inventoryDao.insertItem(
                    InventoryItemEntity(
                        remoteId = remoteId,
                        barcode = dto.barcode,
                        name = dto.name,
                        brand = dto.brand,
                        category = dto.category,
                        quantity = dto.quantity,
                        expiryDate = dto.expiryDate,
                        imageUrl = dto.imageUrl,
                        isSynced = true
                    )
                )
                importedCount++
            }
        }

        return importedCount
    }
}
