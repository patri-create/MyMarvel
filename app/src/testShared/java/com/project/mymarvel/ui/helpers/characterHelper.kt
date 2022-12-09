package com.project.mymarvel.ui.helpers

import com.project.mymarvel.data.server.responses.Image
import com.project.mymarvel.data.server.responses.characters.Character
import com.project.mymarvel.data.server.responses.characters.CharacterDataContainer
import com.project.mymarvel.data.server.responses.characters.CharacterDataWrapper


fun buildRemoteCharacterDataWrapper() = CharacterDataWrapper(
    code = 0,
    status = "status",
    data = buildRemoteCharacterDataContainer(),
    etag = "etag",
    copyright = "copyright",
    attributionHTML = "attributionHTML",
    attributionText = "attributionText"
)

fun buildRemoteCharacterDataContainer() = CharacterDataContainer(
    offset = 0,
    limit = 0,
    total = 0,
    count = 0,
    results = buildRemoteCharacters(0, 1, 2)
)

fun buildRemoteCharacters(vararg id: Int) = id.map {
    Character(
        id = it,
        name = "Name",
        description = "description",
        resourceURI = "https://image",
        thumbnail = buildRemoteImage()
    )
}

fun buildRemoteImage() = Image(
    path = "https://path",
    extension = "jpg"
)