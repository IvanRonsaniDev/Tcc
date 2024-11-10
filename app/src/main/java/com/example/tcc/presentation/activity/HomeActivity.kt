package com.example.tcc.presentation.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.databinding.ActivityHomeBinding


class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentoPrincipal.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnNavigation.setupWithNavController(navController)
    }
}