package com.example.tcc.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.core.extensions.showToast
import com.example.tcc.databinding.ActivityChangePasswordBinding
import com.example.tcc.presentation.viewModel.ChangePasswordViewModel

class ChangePasswordActivity : BaseActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    private val viewModel by viewModels<ChangePasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchData()

        with(binding) {
            btnSave.setOnClickListener {
                when {
                    etNewPassword.text?.toString().isNullOrBlank() ||
                            etConfirmPassword.text?.toString().isNullOrBlank() -> {
                        showToast("Preencha todos os campos")
                    }

                    etNewPassword.text?.toString() != etConfirmPassword.text?.toString() -> {
                        showToast("As senhas devem ser iguais")
                    }

                    (etNewPassword.text?.toString()?.length ?: 0) < 6 -> {
                        showToast("Sua senha deve conter no mínimo 6 números")
                    }

                    etNewPassword.text?.toString() == viewModel.currentPassword -> {
                        showToast("Sua senha não pode ser igual a atual")
                    }

                    else -> {
                        viewModel.savePassword(etNewPassword.text?.toString().orEmpty())
                        showToast("Senha alterada com sucesso!")
                        finish()
                    }
                }
            }
        }
    }

}