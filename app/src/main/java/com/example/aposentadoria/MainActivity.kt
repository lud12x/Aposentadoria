package com.example.aposentadoria

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.aposentadoria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {

            val genero = binding.Genero.selectedItem.toString()
            val idadeTexto = binding.etIdade.text.toString()

            if (idadeTexto.isNotEmpty()) {

                val idade = idadeTexto.toInt()

                binding.tilIdade.error = null

                aposenta(genero, idade)

            } else {

                binding.tilIdade.error =
                    getString(R.string.helper_requerido)
            }
        }
    }

    private fun aposenta(genero: String, idade: Int) {

        val idadeMinima =
            if (genero.equals("Masculino", ignoreCase = true)) {
                65
            } else {
                62
            }

        val resultado = idadeMinima - idade

        if (resultado <= 0) {

            binding.Resultado.text =
                getString(R.string.mensagem_aposentado)

        } else {

            binding.Resultado.text =
                getString(
                    R.string.mensagem_faltam_anos,
                    resultado
                )
        }
    }
}