package com.project.mymarvel.data.server.responses.characters

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataWrapper(
    @Json(name = "code")
    val code: Int,
    @Json(name = "status")
    val status: String,
    @Json(name = "data")
    val data: CharacterDataContainer,
    @Json(name = "etag")
    val etag: String,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "attributionText")
    val attributionText: String,
    @Json(name = "attributionHTML")
    val attributionHTML: String
)
