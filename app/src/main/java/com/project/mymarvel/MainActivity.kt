package com.project.mymarvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
        setContentView(R.layout.activity_main)
    }

    private fun showSplash() {
        Thread.sleep(800)
        installSplashScreen()
    }
}