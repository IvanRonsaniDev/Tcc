package com.example.tcc.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.core.extensions.setMaxLength
import com.example.tcc.databinding.ActivityLoginBinding
import com.example.tcc.presentation.viewModel.LoginViewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    private var isTeacher: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        isTeacher = intent.extras?.getBoolean(IS_TEACHER) ?: false
        setContentView(binding.root)

        binding.matricula.hint = if (isTeacher) "CPF" else "Matrícula"
        binding.matricula.setMaxLength(if (isTeacher) 11 else 10)

        binding.entrar.setOnClickListener {
            if (!areFieldsFilled()) {
                Toast.makeText(this, "Os campos não podem ser vazios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.isUserValid(
                binding.matricula.text.toString(),
                binding.senha.text.toString(),
                isTeacher
            )
        }

        viewModel.isUserValid.observe(this) {
            if (it) {
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
                finish()
            } else {
                Toast.makeText(this, "Esse usuário nao existe!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun areFieldsFilled() = binding.matricula.text.toString().isNotEmpty() &&
            binding.senha.text.toString().isNotEmpty()

    companion object {
        const val IS_TEACHER = "IS_TEACHER"
    }
}
