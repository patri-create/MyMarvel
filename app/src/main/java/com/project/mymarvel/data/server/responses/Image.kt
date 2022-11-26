package com.project.mymarvel.data.server.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "path")
    val path: String,
    @Json(name = "extension")
    val extension: String,
)
