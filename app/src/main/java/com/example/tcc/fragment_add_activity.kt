package com.example.tcc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.databinding.FragmentAddActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class fragment_add_activity :  AppCompatActivity() {
    private lateinit var binding: FragmentAddActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding = FragmentAddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receber a data do intent
        val selectedDate = intent.getStringExtra("SELECTED_DATE") ?: "Data não selecionada"
        binding.tvDate.text = selectedDate

        binding.btnAdicionar.setOnClickListener {
            val title = binding.etConteudo.text.toString()
            val description = binding.etDescricao.text.toString()
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(binding.tvDate.text.toString()) ?: Date()
            //Aqui nao sabia oq colocar dai eu meti o fodase
            val disciplineId: Int

            val newActivity = ActivityEntity(
                title = title,
                description = description,
                date = date,
                disciplineId = 1

            )

            // Salvar no banco de dados
            CoroutineScope(Dispatchers.IO).launch {
                AppDataBase.getInstance(applicationContext).activityDAO().insert(newActivity)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@fragment_add_activity, "Atividade adicionada com sucesso", Toast.LENGTH_SHORT).show()
                    finish() // Fechar a atividade após adicionar
                }
            }
        }
    }


    }



