package com.project.mymarvel.data.server.responses.comics

import com.project.mymarvel.data.server.responses.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comic(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "thumbnail")
    val thumbnail: Image
)
