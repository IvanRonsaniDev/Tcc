package com.example.tcc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tcc.core.base.BaseActivity

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        matricula = findViewById(R.id.matricula)
        senha = findViewById(R.id.senha)

        val btnEnviar: Button = findViewById(R.id.entrar)
        btnEnviar.setOnClickListener {
            if (!areFieldsFilled()) {
                Toast.makeText(this, "Os campos não podem ser vazios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener


            }

            require(isUserValid()) {
                Toast.makeText(this, "Esse usuário nao existe!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)

            finish()

        }


    }

    private fun areFieldsFilled() = matricula.text.toString().isNotEmpty() &&
            senha.text.toString().isNotEmpty()

    private fun isUserValid() =
        matricula.text.toString() == "0123456789" && senha.text.toString() == "28062007"


    private lateinit var matricula: EditText
    private lateinit var senha: EditText

}
