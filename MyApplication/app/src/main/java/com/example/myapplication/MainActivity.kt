package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var txtNombre:EditText
    private lateinit var txtNumero:EditText
    private lateinit var btnAceptar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         txtNombre=findViewById(R.id.txtName)
         txtNumero=findViewById(R.id.txtNumero)
         btnAceptar=findViewById(R.id.btnAceptar)

        btnAceptar.setOnClickListener{
            mostrar()
        }

        btnAceptar.setOnClickListener {
            xxx()
        }
    }

    private fun mostrar() {
        val nombre = txtNombre.text
        val nota = txtNumero.text.toString().toInt()

        Toast.makeText(this,"Hola $nombre su nota es $nota", Toast.LENGTH_LONG).show()
    }
    private fun xxx():Int{
        return 0
    }
}