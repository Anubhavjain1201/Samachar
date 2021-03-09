package com.example.samachar.network

import com.squareup.moshi.Json

data class Samachar(


    @Json(name = "source")
    val source: Source?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "author")
    val author: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "url")
    val url: String?,

    @Json(name = "urlToImage")
    val imageUrl: String?,

    @Json(name = "publishedAt")
    val published: String?,

    @Json(name = "content")
    val content: String?
)
