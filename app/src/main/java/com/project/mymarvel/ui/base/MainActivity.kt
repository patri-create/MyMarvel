package com.project.mymarvel.ui.base

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.project.mymarvel.R
import com.project.mymarvel.common.base.BaseActivity
import com.project.mymarvel.databinding.ActivityMainBinding
import com.project.mymarvel.ui.home.HomeFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToHomeFragment()
    }

    private fun showSplash() {
        Thread.sleep(800)
        installSplashScreen()
    }

    private fun goToHomeFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.view_fragment, HomeFragment.newInstance())
            .commit()
    }
}