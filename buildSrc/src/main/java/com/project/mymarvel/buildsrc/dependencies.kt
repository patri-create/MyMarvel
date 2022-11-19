package com.project.mymarvel.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.2"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.44.0"

    object Kotlin {
        private const val version = "1.7.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object AndroidX {

        const val core = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        const val material = "com.google.android.material:material:1.6.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val splash = "androidx.core:core-splashscreen:1.0.0"

        object Navigation {
            private const val version = "2.5.3"
            const val NavigationFragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val NavigationUI = "androidx.navigation:navigation-ui-ktx:$version"
            const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object SwipeLayout {
            private const val version = "1.1.0"
            const val swipe = "androidx.swiperefreshlayout:swiperefreshlayout:$version"
        }

        object RecycleView {
            private const val version = "1.2.1"
            const val recycleview = "androidx.recyclerview:recyclerview:$version"
            const val recycleviewSelection = "androidx.recyclerview:recyclerview-selection:1.1.0"
        }

        object Drawer {
            private const val version = "1.1.1"
            const val drawer = "androidx.drawerlayout:drawerlayout:1.1.1"
        }

        object Test {
            private const val version = "1.4.0"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.3"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }
            object Espresso{
                private const val version="3.4.0"
                const val contrib = "androidx.test.espresso:espresso-contrib:$version"
            }
        }

    }

    object Glide {

        private const val version = "4.13.2"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object DotsIndicator {
        private const val version = "1.2.3"
        const val dotsIndicator = "ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:$version"
    }

    object OkHttp3 {
        private const val version = "4.9.3"
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Arrow {
        private const val version = "1.1.3"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object Hilt {
        private const val version = "2.44.2"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val test = "com.google.dagger:hilt-android-testing:$version"
    }
}