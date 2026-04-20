package com.example.foodexpiryscanner.data.remote

data class BackendItemDto(
    val _id: String? = null,
    val barcode: String,
    val name: String,
    val brand: String,
    val category: String,
    val quantity: Int,
    val expiryDate: String,
    val imageUrl: String,
    val notes: String
)
