package com.example.crud_api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import modelo.Producto

class Adaptador: BaseAdapter {
    lateinit var contexto:Context
    var layout : Int=0
    lateinit var listaProductos:List<Producto>

    constructor(context: Context,layout:Int,listaProducto: List<Producto>){
        this.contexto = contexto
        this.layout = layout
        this.listaProductos=listaProductos
    }

    override fun getCount(): Int {
        return listaProductos.size
    }

    override fun getItem(position: Int): Any {
        return listaProductos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v:View
        var inflater:LayoutInflater=LayoutInflater.from(contexto)
        v = inflater.inflate(R.layout.listar_productos,null)
        val txt_Codigo: TextView=v.findViewById(R.id.txt_Codigo)
        txt_Codigo.text = listaProductos[position].codigo.toString()
        val txt_Nombre: TextView=v.findViewById(R.id.txt_Nombre)
        txt_Nombre.text = listaProductos[position].nombre
        val txt_Precio: TextView=v.findViewById(R.id.txt_Precio)
        txt_Precio.text = listaProductos[position].precio.toString()
        val txt_Categoria: TextView=v.findViewById(R.id.txt_Categoria)
        txt_Categoria.text = listaProductos[position].categoria.nombre
        return v
    }
}