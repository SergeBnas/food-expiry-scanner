package com.example.foodexpiryscanner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: String = "",
    val barcode: String,
    val name: String,
    val brand: String,
    val category: String,
    val quantity: Int,
    val expiryDate: String,
    val imageUrl: String = "",
    val notes: String = "",
    val isSynced: Boolean = false
)
