package com.project.mymarvel.ui.helpers

import com.project.mymarvel.data.server.responses.events.Event
import com.project.mymarvel.data.server.responses.events.EventDataContainer
import com.project.mymarvel.data.server.responses.events.EventDataWrapper

fun buildRemoteEventDataWrapper() = EventDataWrapper(
    code = 0,
    status = "status",
    data = buildRemoteEventDataContainer(),
    etag = "etag",
    copyright = "copyright",
    attributionHTML = "attributionHTML",
    attributionText = "attributionText"
)

fun buildRemoteEventDataContainer() = EventDataContainer(
    offset = 0,
    limit = 0,
    total = 0,
    count = 0,
    results = buildRemoteEvents(0, 1, 2)
)

fun buildRemoteEvents(vararg id: Int) = id.map {
    Event(
        id = it,
        title = "Title",
        description = "description",
        thumbnail = buildRemoteImage()
    )
}

