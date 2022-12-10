package com.project.mymarvel.data.server

import com.project.mymarvel.data.server.responses.Image as ImageDto
import com.project.mymarvel.data.server.responses.characters.Character as CharacterDto
import com.project.mymarvel.data.server.responses.comics.Comic as ComicDto
import com.project.mymarvel.data.server.responses.events.Event as EventDto
import com.project.mymarvel.domain.Hero
import com.project.mymarvel.domain.Comic
import com.project.mymarvel.domain.Event

fun CharacterDto.toDomain(): Hero =
    Hero(
        id,
        name,
        thumbnail.toDomain(),
        description
    )

fun EventDto.toDomain(): Event =
    Event(
        id,
        thumbnail.toDomain(),
        title,
        description
    )

fun ComicDto.toDomain(): Comic =
    Comic(
        id,
        title,
        thumbnail.toDomain(),
        description ?: ""
    )

fun ImageDto.toDomain(): String = "$path.$extension"