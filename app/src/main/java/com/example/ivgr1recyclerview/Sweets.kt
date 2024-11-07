package com.example.ivgr1recyclerview

data class Sweets(
    val name: String,
    val price: Double,
    var isFavorite: Boolean = false,
    var addToCartCounter: Int = 0
)
