package com.example.foodexpiryscanner.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000bJ\u0006\u0010\u0017\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r\u00a8\u0006\u0019"}, d2 = {"Lcom/example/foodexpiryscanner/viewmodel/InventoryViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_syncMessage", "Landroidx/lifecycle/MutableLiveData;", "", "allItems", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/foodexpiryscanner/data/local/InventoryItemEntity;", "getAllItems", "()Landroidx/lifecycle/LiveData;", "repository", "Lcom/example/foodexpiryscanner/data/repository/InventoryRepository;", "syncMessage", "getSyncMessage", "clearSyncMessage", "", "deleteItem", "item", "insertItem", "syncWithBackend", "updateItem", "app_debug"})
public final class InventoryViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.foodexpiryscanner.data.repository.InventoryRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.foodexpiryscanner.data.local.InventoryItemEntity>> allItems = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _syncMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> syncMessage = null;
    
    public InventoryViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.foodexpiryscanner.data.local.InventoryItemEntity>> getAllItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSyncMessage() {
        return null;
    }
    
    public final void insertItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item) {
    }
    
    public final void updateItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item) {
    }
    
    public final void deleteItem(@org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.local.InventoryItemEntity item) {
    }
    
    public final void clearSyncMessage() {
    }
    
    public final void syncWithBackend() {
    }
}