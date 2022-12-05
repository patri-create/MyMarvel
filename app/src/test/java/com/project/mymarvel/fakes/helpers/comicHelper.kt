package com.project.mymarvel.fakes.helpers

import com.project.mymarvel.data.server.responses.comics.Comic
import com.project.mymarvel.data.server.responses.comics.ComicDataContainer
import com.project.mymarvel.data.server.responses.comics.ComicDataWrapper


fun buildRemoteComicDataWrapper() = ComicDataWrapper(
    code = 0,
    status = "status",
    data = buildRemoteComicDataContainer(),
    etag = "etag",
    copyright = "copyright",
    attributionHTML = "attributionHTML",
    attributionText = "attributionText"
)

fun buildRemoteComicDataContainer() = ComicDataContainer(
    offset = 0,
    limit = 0,
    total = 0,
    count = 0,
    results = buildRemoteComics(0, 1, 2)
)

fun buildRemoteComics(vararg id: Int) = id.map {
    Comic(
        id = it,
        title = "Title",
        description = "description",
        resourceURI = "https://image",
        thumbnail = buildRemoteImage()
    )
}