package com.example.tcc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tcc.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
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