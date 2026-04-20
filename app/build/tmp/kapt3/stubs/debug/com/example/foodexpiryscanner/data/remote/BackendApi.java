package com.example.foodexpiryscanner.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\r0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/example/foodexpiryscanner/data/remote/BackendApi;", "", "createItem", "Lretrofit2/Response;", "Lcom/example/foodexpiryscanner/data/remote/BackendItemDto;", "item", "(Lcom/example/foodexpiryscanner/data/remote/BackendItemDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteItem", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItems", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateItem", "(Ljava/lang/String;Lcom/example/foodexpiryscanner/data/remote/BackendItemDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface BackendApi {
    
    @retrofit2.http.GET(value = "api/items")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.foodexpiryscanner.data.remote.BackendItemDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/items")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createItem(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.remote.BackendItemDto item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.foodexpiryscanner.data.remote.BackendItemDto>> $completion);
    
    @retrofit2.http.PUT(value = "api/items/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateItem(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.foodexpiryscanner.data.remote.BackendItemDto item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.foodexpiryscanner.data.remote.BackendItemDto>> $completion);
    
    @retrofit2.http.DELETE(value = "api/items/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteItem(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
}