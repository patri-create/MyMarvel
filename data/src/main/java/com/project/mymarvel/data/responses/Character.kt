package com.project.mymarvel.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "thumbnail")
    val thumbnail: Image
)
