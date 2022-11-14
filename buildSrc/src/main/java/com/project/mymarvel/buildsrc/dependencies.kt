package com.project.mymarvel.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.2"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.42.0"

    object Kotlin {
        private const val version = "1.7.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }
}