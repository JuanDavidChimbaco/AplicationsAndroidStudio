package com.example.crud_api

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import modelo.Categoria
import org.json.JSONException


class MainActivity : AppCompatActivity() {
    lateinit var txtCodigo:EditText
    lateinit var txtNombre:EditText
    lateinit var txtPrecio:EditText
    lateinit var txtCategoria:TextView

    lateinit var cbCategoria:Spinner

    lateinit var btnAgregar:Button
    lateinit var btnConsultar:Button
    lateinit var btnEliminar:Button
    lateinit var btnModificar:Button

    lateinit var listaCategorias:MutableList<Categoria>

    private var idCategoria:Int=0
    private var idProducto:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCodigo = findViewById(R.id.txt_Codigo)
        txtNombre = findViewById(R.id.txt_Nombre)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtCategoria = findViewById(R.id.txtResultado)

        btnAgregar = findViewById(R.id.btnAgregar)
        btnModificar = findViewById(R.id.btnModificar)
        btnConsultar = findViewById(R.id.btnConsultar)
        btnEliminar = findViewById(R.id.btnEliminar)

        cbCategoria = findViewById(R.id.cbCategoria)
        listaCategorias = mutableListOf<Categoria>()
        listaCategorias.add(Categoria(0, "Seleccione una categoría"))

        obtenerCategoria()
        /* crear el adaptador donde se capturan los datos de la lista categorias
        * el adaptador se asocia al control visual de tipo spiner que es como un combobox*/
        val adaptador = ArrayAdapter<Categoria>(
            this,
            android.R.layout.simple_spinner_item,
            listaCategorias
        )
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cbCategoria.adapter = adaptador


        btnAgregar.setOnClickListener { agregar() }
        btnConsultar.setOnClickListener{consultar()}
        btnModificar.setOnClickListener{actualizar()}
        btnEliminar.setOnClickListener { mostrarCuadroDeDialogoConfirmacion() }


        cbCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Obtén el elemento seleccionado del Spinner
                val elementoSeleccionado = listaCategorias[position]
                // Accede a los atributos del elemento seleccionado
                idCategoria = elementoSeleccionado.id
                var nombreCategoria = elementoSeleccionado.nombre
                txtCategoria.text = nombreCategoria
                // Puedes hacer algo con los datos obtenidos, por ejemplo, mostrarlos en un Toast
                Toast.makeText(this@MainActivity, "ID: $idCategoria, Nombre: $nombreCategoria", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Manejar el caso donde no se ha seleccionado ningún elemento
            }
        }


    }

    /**
     * obtener las categorias
     */
    private fun obtenerCategoria(){
        val url = "http://10.192.66.49:8000/categoria"
        val queue = Volley.newRequestQueue(this)
        val jsonCategorias = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener { response ->
                try {
                    for (i in 0 until response.length() ) {
                        val jsonObjectCategoria = response.getJSONObject(i)
                        val id = jsonObjectCategoria.getInt("id")
                        val nombre = jsonObjectCategoria.getString("catNombre")
                        val categoria = Categoria(id, nombre)
                        listaCategorias.add(categoria)
                    }
                    val adaptador = ArrayAdapter<Categoria>(
                        this,
                        android.R.layout.simple_spinner_item,
                        listaCategorias
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    cbCategoria.adapter = adaptador
                }catch (e:JSONException){
                    e.printStackTrace()
            }
        }, Response.ErrorListener{
            error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(jsonCategorias)
    }

    private fun agregar(){
        val url ="http://10.192.66.49:8000/producto"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(
            Method.POST,url,
            Response.Listener<String>{
                Toast.makeText(this,"Producto Agregado Exitosamente",Toast.LENGTH_LONG).show()
                limpid()
            },Response.ErrorListener { error ->
                val statusCode = error.networkResponse?.statusCode ?: -1
                val errorMessage = String(error.networkResponse?.data ?: ByteArray(0))
                Log.e("VolleyError", "Status code: $statusCode, Error message: $errorMessage")
                Toast.makeText(this, "Error al Agregar $errorMessage $statusCode", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parameters = hashMapOf<String,String>()
                parameters.put("proCodigo", txtCodigo.text.toString())
                parameters.put("proNombre", txtNombre.text.toString())
                parameters.put("proPrecio", txtPrecio.text.toString())
                parameters.put("proCategoria", idCategoria.toString())
                return parameters
            }
        }
        queue.add(resultadoPost)
    }

    fun limpid(){
        txtCodigo.text.clear()
        txtNombre.text.clear()
        txtPrecio.text.clear()
        cbCategoria.setSelection(0)
    }

    private fun borrar(){
        val url = "http://10.192.66.49:8000/producto/$idProducto"
        val queue = Volley.newRequestQueue(this)
        Log.i(TAG, "id producto que se borrara $idProducto")
        val resultDelete = object : StringRequest(Request.Method.DELETE, url,
            Response.Listener {
                Toast.makeText(this, "Producto Eliminado", Toast.LENGTH_LONG).show()
                limpid()
            }, Response.ErrorListener { error ->
                val statusCode = error.networkResponse?.statusCode ?: -1
                val errorMessage = String(error.networkResponse?.data ?: ByteArray(0))
                Log.e("VolleyError", "Status code: $statusCode, Error message: $errorMessage")
                Toast.makeText(
                    this,
                    "Error Al Eliminar 1$errorMessage $statusCode",
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {
        }
        queue.add(resultDelete)
    }

    private fun mostrarCuadroDeDialogoConfirmacion() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmación")
        builder.setMessage("¿Estás seguro de que deseas eliminar este producto?")
        builder.setPositiveButton("Sí") { dialog, which ->
            // Si el usuario confirma, proceder con la eliminación
            borrar()
        }
        builder.setNegativeButton("Cancelar", null) // No hacer nada si el usuario cancela
        builder.show()
    }

    private fun consultar(){
        val id = txtCodigo.text.toString()
        val url = "http://10.192.66.49:8000/producto/codigo/$id"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener{ response ->
                txtCodigo.setText(response.getString("proCodigo"))
                txtNombre.setText(response.getString("proNombre"))
                txtPrecio.setText(response.getString("proPrecio"))
                idCategoria = response.getString("proCategoria").toInt()
                idProducto = response.getString("id").toInt()
                cbCategoria.setSelection(idCategoria)
                Log.i(TAG, "producto id: $idProducto - categoria id: $idCategoria")
            }, Response.ErrorListener{ error ->
                val statusCode = error.networkResponse?.statusCode ?: -1
                val errorMessage = String(error.networkResponse?.data ?: ByteArray(0))
                Log.e("VolleyError", "Status code: $statusCode, Error message: $errorMessage")
                Toast.makeText(this, "Error Al Consultar 1$errorMessage $statusCode", Toast.LENGTH_LONG).show()
        }
        )
        queue.add(jsonObjectRequest)
    }

    private fun actualizar() {
        Log.i(TAG, "id producto que se modificara $idProducto")
        val url = "http://10.192.66.49:8000/producto/$idProducto"
        val queue = Volley.newRequestQueue(this)
        val resultadoPut = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> {respose->
                Toast.makeText(this, "Producto Actualizado Exitosamente", Toast.LENGTH_LONG).show()
                limpid()
            }, Response.ErrorListener { error ->
                val statusCode = error.networkResponse?.statusCode ?: -1
                Log.e("VolleyError", "Status code: $statusCode,Error al Actualizar")
                Toast.makeText(this, "Error al Actualizar $statusCode", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val parameters = hashMapOf<String, String>()
                parameters.put("proCodigo", txtCodigo.text.toString())
                parameters.put("proNombre", txtNombre.text.toString())
                parameters.put("proPrecio", txtPrecio.text.toString())
                parameters.put("proCategoria", idCategoria.toString())
                return parameters
            }
        }
        queue.add(resultadoPut)
    }

}