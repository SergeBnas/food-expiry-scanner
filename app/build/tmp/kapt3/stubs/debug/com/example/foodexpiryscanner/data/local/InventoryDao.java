package com.example.foodexpiryscanner.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J(\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ(\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u0019\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006!"}, d2 = {"Lcom/example/foodexpiryscanner/data/local/InventoryDao;", "", "deleteAllItems", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteItem", "item", "Lcom/example/foodexpiryscanner/data/local/InventoryItemEntity;", "(Lcom/example/foodexpiryscanner/data/local/InventoryItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllItems", "Landroidx/lifecycle/LiveData;", "", "getDuplicate", "barcode", "", "name", "expiryDate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemByBarcode", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemById", "itemId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemByRemoteId", "remoteId", "getUnsyncedItems", "insertItem", "markItemSynced", "isSynced", "", "(IZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateItem", "app_debug"})
@androidx.room.Dao()
public abstract interface InventoryDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_items ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.foodexpiryscanner.data.local.InventoryItemEntity>> getAllItems();
    
    @androidx.room.Query(value = "SELECT * FROM inventory_items WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getItemById(int itemId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.foodexpiryscanner.data.local.InventoryItemEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_items WHERE isSynced = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsyncedItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.foodexpiryscanner.data.local.InventoryItemEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE inventory_items SET isSynced = :isSynced, remoteId = :remoteId WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markItemSynced(int itemId, boolean isSynced, @org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_items WHERE barcode = :barcode LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getItemByBarcode(@org.jetbrains.annotations.NotNull()
    java.lang.String barcode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.foodexpiryscanner.data.local.InventoryItemEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_items WHERE remoteId = :remoteId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getItemByRemoteId(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.foodexpiryscanner.data.local.InventoryItemEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_items WHERE barcode = :barcode AND name = :name AND expiryDate = :expiryDate LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDuplicate(@org.jetbrains.annotations.NotNull()
    java.lang.String barcode, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String expiryDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.foodexpiryscanner.data.local.InventoryItemEntity> $completion);
    
    @androidx.room.Query(value = "DELETE FROM inventory_items")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}