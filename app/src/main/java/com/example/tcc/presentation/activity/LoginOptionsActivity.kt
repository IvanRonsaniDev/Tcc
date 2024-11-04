package com.example.tcc.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tcc.databinding.ActivityLoginOptionsBinding

class LoginOptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStudent.setOnClickListener {
            openLoginScreen(isTeacher = false)
        }
        binding.btTeacher.setOnClickListener {
            openLoginScreen(isTeacher = true)
        }
    }

    private fun openLoginScreen(isTeacher: Boolean) {
        Intent(this, LoginActivity::class.java).apply {
            putExtra(LoginActivity.IS_TEACHER, isTeacher)
            startActivity(this)
        }
    }

}