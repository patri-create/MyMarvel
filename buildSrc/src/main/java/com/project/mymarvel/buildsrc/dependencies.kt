package com.project.mymarvel.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.2"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.44.0"
    const val playServicesLocation = "com.google.android.gms:play-services-location:19.0.1"

    object Kotlin {
        private const val version = "1.7.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.6.0"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {

        const val core = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        const val material = "com.google.android.material:material:1.6.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val splash = "androidx.core:core-splashscreen:1.0.0"

        object Activity {
            private const val version = "1.4.0"
            const val ktx = "androidx.activity:activity-ktx:1.4.0"
        }

        object Lifecycle {
            private const val version = "2.4.1"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

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

    object Shimmer {
        private const val version = "0.4.0"
        const val shimmer = "com.facebook.shimmer:shimmer:$version"
    }

    object OkHttp3 {
        private const val version = "4.9.3"
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Moshi {
        private const val version = "1.14.0"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
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

    object JavaX {
        const val inject = "javax.inject:javax.inject:1"
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object Mockito {
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:4.0.0"
        const val inline = "org.mockito:mockito-inline:4.4.0"
    }

    object Turbine {
        private const val version = "0.7.0"
        const val turbine =  "app.cash.turbine:turbine:0.7.0"
    }
}