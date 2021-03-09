package com.example.samachar.network

import com.squareup.moshi.Json

data class Model(

    @Json(name = "status")
    val status: String?,

    @Json(name = "totalResults")
    val results: Int?,

    @Json(name = "articles")
    val articles: List<Samachar>
)