package com.example.nammakathey
import java.io.Serializable

data class StoryPage(
    val pageNumber: Int,
    val pageTitle: String,
    val text: String,
    val pageTitleKn: String = "",
    val textKn: String = ""
) : Serializable