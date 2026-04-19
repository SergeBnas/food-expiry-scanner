package com.example.foodexpiryscanner.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0016\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u0017\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0018\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/foodexpiryscanner/data/repository/InventoryRepository;", "", "inventoryDao", "Lcom/example/foodexpiryscanner/data/local/InventoryDao;", "(Lcom/example/foodexpiryscanner/data/local/InventoryDao;)V", "allItems", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/foodexpiryscanner/data/local/InventoryItemEntity;", "getAllItems", "()Landroidx/lifecycle/LiveData;", "deleteAllItems", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteItem", "item", "(Lcom/example/foodexpiryscanner/data/local/InventoryItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemById", "itemId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertItem", "pullRemoteItems", "syncPendingItems", "updateItem", "app_debug"})
public final class InventoryRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.foodexpiryscanner.data.local.InventoryDao inventoryDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.foodexpiryscanner.data.local.InventoryItemEntity>> allItems = null;
    
    public InventoryRepository(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryDao inventoryDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.foodexpiryscanner.data.local.InventoryItemEntity>> getAllItems() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getItemById(int itemId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.foodexpiryscanner.data.local.InventoryItemEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAllItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncPendingItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object pullRemoteItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}