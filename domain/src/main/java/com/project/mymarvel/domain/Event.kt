package com.project.mymarvel.domain

data class Event(
    override var id: Int,
    override var image: String,
    override var title: String,
    override var description: String
) : EventItem {
}