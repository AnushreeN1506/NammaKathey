package com.example.nammakathey
data class Hero(
    val id: String,
    val name: String,
    val era: String,
    val role: String,
    val imageRes: String,
    val summary: String,
    val values: List<String>,
    val storyPages: List<StoryPage>,
    val quiz: List<Quiz>
)