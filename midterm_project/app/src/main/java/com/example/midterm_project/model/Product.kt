package com.example.midterm_project.model

import java.io.Serializable

data class Product(
    val id: Int,
    val productName: String,
    val description: String,
    val price: Int,
    val image: String
) : Serializable