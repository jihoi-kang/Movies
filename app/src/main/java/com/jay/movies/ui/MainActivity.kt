package com.jay.movies.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.jay.movies.R
import com.jay.movies.base.BaseActivity
import com.jay.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
) {
    private val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_container) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bnvNavigation.setupWithNavController(navController)
        binding.bnvNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movieFragment -> navController.popBackStack(R.id.movieFragment, false)
                else -> NavigationUI.onNavDestinationSelected(it, navController)
            }
        }
    }

}