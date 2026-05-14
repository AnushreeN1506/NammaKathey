package com.example.nammakathey
data class Quiz(
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)