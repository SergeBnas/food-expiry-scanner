package com.example.foodexpiryscanner.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: InventoryItemEntity)

    @Update
    suspend fun updateItem(item: InventoryItemEntity)

    @Delete
    suspend fun deleteItem(item: InventoryItemEntity)

    @Query("SELECT * FROM inventory_items ORDER BY id DESC")
    fun getAllItems(): LiveData<List<InventoryItemEntity>>

    @Query("SELECT * FROM inventory_items WHERE id = :itemId")
    suspend fun getItemById(itemId: Int): InventoryItemEntity?

    @Query("SELECT * FROM inventory_items WHERE isSynced = 0")
    suspend fun getUnsyncedItems(): List<InventoryItemEntity>

    @Query("UPDATE inventory_items SET isSynced = :isSynced, remoteId = :remoteId WHERE id = :itemId")
    suspend fun markItemSynced(itemId: Int, isSynced: Boolean, remoteId: String = "")

    @Query("SELECT * FROM inventory_items WHERE barcode = :barcode LIMIT 1")
    suspend fun getItemByBarcode(barcode: String): InventoryItemEntity?

    @Query("SELECT * FROM inventory_items WHERE remoteId = :remoteId LIMIT 1")
    suspend fun getItemByRemoteId(remoteId: String): InventoryItemEntity?

    @Query("SELECT * FROM inventory_items WHERE barcode = :barcode AND name = :name AND expiryDate = :expiryDate LIMIT 1")
    suspend fun getDuplicate(barcode: String, name: String, expiryDate: String): InventoryItemEntity?

    @Query("DELETE FROM inventory_items")
    suspend fun deleteAllItems()
}
