package com.project.mymarvel.ui.base

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.project.mymarvel.R
import com.project.mymarvel.common.LocaleManager
import com.project.mymarvel.common.LocationHelper
import com.project.mymarvel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var responsePermissionRequest: ActivityResultLauncher<String>

    @Inject
    lateinit var locationHelper: LocationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instances()
    }

    private fun instances() {
        observers()
        configurations()
    }

    private fun observers() {
        responsePermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            ::checkPermissions
        )
        binding.toolbar.toolbarLocationIcon.setOnClickListener {
            responsePermissionRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun configureAppBar() {
        setSupportActionBar(binding.toolbar.toolbarComponent)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_dest,
                R.id.comics_dest,
                R.id.settings_dest
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun checkPermissions(isGranted: Boolean) {
        if (isGranted) {
            locationHelper.getLocation { binding.toolbar.toolbarLocationText.text = it }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.newInstance(newBase).setLocale())
    }
}