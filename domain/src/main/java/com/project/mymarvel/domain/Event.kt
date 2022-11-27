package com.project.mymarvel.domain

class Event(
    override var image: String,
    override var title: String,
    override var description: String
) : EventItem {
}