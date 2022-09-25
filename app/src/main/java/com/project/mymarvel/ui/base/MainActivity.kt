package com.project.mymarvel.ui.base

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.mymarvel.R
import com.project.mymarvel.common.base.BaseActivity
import com.project.mymarvel.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureNavigation()
        navigateToHomeFragment()
    }

    private fun showSplash() {
        Thread.sleep(800)
        installSplashScreen()
    }

    private fun configureNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun navigateToHomeFragment() {
        navController.navigate(R.id.nav_home)
    }
}