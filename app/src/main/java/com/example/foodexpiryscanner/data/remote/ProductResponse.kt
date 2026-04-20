package com.example.foodexpiryscanner.data.remote

data class ProductResponse(
    val status: Int,
    val product: Product?
)

data class Product(
    val product_name: String?,
    val brands: String?,
    val categories: String?,
    val image_url: String?
)
