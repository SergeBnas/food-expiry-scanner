package com.example.foodexpiryscanner.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0015"}, d2 = {"Lcom/example/foodexpiryscanner/data/remote/ApiModule;", "", "()V", "BACKEND_BASE_URL", "", "OPEN_FOOD_BASE_URL", "backendApi", "Lcom/example/foodexpiryscanner/data/remote/BackendApi;", "getBackendApi", "()Lcom/example/foodexpiryscanner/data/remote/BackendApi;", "backendApi$delegate", "Lkotlin/Lazy;", "client", "Lokhttp3/OkHttpClient;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor;", "openFoodApi", "Lcom/example/foodexpiryscanner/data/remote/OpenFoodFactsApi;", "getOpenFoodApi", "()Lcom/example/foodexpiryscanner/data/remote/OpenFoodFactsApi;", "openFoodApi$delegate", "app_debug"})
public final class ApiModule {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String OPEN_FOOD_BASE_URL = "https://world.openfoodfacts.org/";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BACKEND_BASE_URL = "http://10.0.2.2:5000/";
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.logging.HttpLoggingInterceptor logger = null;
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy openFoodApi$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy backendApi$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.foodexpiryscanner.data.remote.ApiModule INSTANCE = null;
    
    private ApiModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.foodexpiryscanner.data.remote.OpenFoodFactsApi getOpenFoodApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.foodexpiryscanner.data.remote.BackendApi getBackendApi() {
        return null;
    }
}