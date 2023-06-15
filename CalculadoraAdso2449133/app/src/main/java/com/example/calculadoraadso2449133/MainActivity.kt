package com.example.calculadoraadso2449133

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var txtNumero1:EditText
    private lateinit var txtNumero2:EditText
    private lateinit var txtResultado:EditText
    private lateinit var btnSumar:Button
    private lateinit var btnResta:Button
    private lateinit var btnMultiplicar:Button
    private lateinit var btnDividir:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtNumero1 = findViewById(R.id.txtNumero1)
        txtNumero2 = findViewById(R.id.txtNumero2)
        txtResultado = findViewById(R.id.txtResultado)

        btnSumar = findViewById(R.id.btnSumar)
        btnResta = findViewById(R.id.btnRestar)
        btnDividir = findViewById(R.id.btnDivir)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)

        btnSumar.setOnClickListener {
            suma()
         }
        btnResta.setOnClickListener {
            resta()
        }
        btnDividir.setOnClickListener {
            divir()
        }
        btnMultiplicar.setOnClickListener {
            multiplicar()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun multiplicar() {
        if (estaVacio()){
                txtResultado.setText("Flatan Datos")
            }else{
                val numero1 = txtNumero1.text.toString().toDouble()
                val numero2 = txtNumero2.text.toString().toDouble()
                val multiplicacion = numero1*numero2
                txtResultado.setText("$multiplicacion")
        }
    }

    private  fun estaVacio():Boolean{
        if (txtNumero1.text.toString().isEmpty() || txtNumero2.text.toString().isEmpty()){
            return true
        }
        return false
    }
    @SuppressLint("SetTextI18n")
    private fun divir() {
        if (estaVacio()){
            txtResultado.setText("Flatan Datos")
        }else {
            val numero1 = txtNumero1.text.toString().toDouble()
            val numero2 = txtNumero2.text.toString().toDouble()
            if (numero2==0.0){
                txtResultado.setText("No divicible por 0")
            }else{
                val divicion = numero1 / numero2
                txtResultado.setText("$divicion")
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun resta() {
        if (estaVacio()){
            txtResultado.setText("Flatan Datos")
        }else {
            val numero1 = txtNumero1.text.toString().toInt()
            val numero2 = txtNumero2.text.toString().toInt()
            val resta = numero1 - numero2
            txtResultado.setText("$resta")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun suma() {
        if (estaVacio()){
            txtResultado.setText("Flatan Datos")
        }else{
            val numero1 = txtNumero1.text.toString().toInt()
            val numero2 = txtNumero2.text.toString().toInt()
            val suma = numero1+numero2
            txtResultado.setText("$suma")
        }

    }


}