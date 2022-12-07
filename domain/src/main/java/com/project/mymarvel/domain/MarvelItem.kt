package com.project.mymarvel.domain

import java.io.Serializable

interface MarvelItem: Serializable {
    var id: Int
    var name: String
    var image: String
    var description: String
}