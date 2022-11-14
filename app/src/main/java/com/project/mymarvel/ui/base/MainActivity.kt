package com.project.mymarvel.ui.base

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.project.mymarvel.R
import com.project.mymarvel.common.base.BaseActivity
import com.project.mymarvel.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurations()
    }

    private fun showSplash() {
        Thread.sleep(800)
        installSplashScreen()
    }

    private fun configurations() {
        configureNavigation()
        configureAppBar()
    }

    private fun configureNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun configureAppBar() {
        setSupportActionBar(binding.toolbar.toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_dest
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}