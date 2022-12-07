package com.project.mymarvel.domain

data class Hero(
    override var id: Int,
    override var name: String,
    override var image: String,
    override var description: String
) : MarvelItem