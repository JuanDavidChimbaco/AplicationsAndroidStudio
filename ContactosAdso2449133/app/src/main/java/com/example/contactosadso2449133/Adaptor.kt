package com.example.contactosadso2449133

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Adaptor: BaseAdapter {
    //Attributes
    private lateinit var context:Context
    private var layout:Int=0
    private lateinit var listPersonas: List<Persona>


    /**
     * @constructor que inicializa el objeto
     */
    constructor(context: Context, layout: Int, listPersonas: List<Persona>){
        this.context = context
        this.layout=layout
        this.listPersonas=listPersonas
    }

    /**
     * Obtener el tamaño de la lista del adaptado
     */
    override fun getCount(): Int {
        return listPersonas.size
    }

    /**
     * Obtener el item del elemento deacuerdo a la posicion
     */
    override fun getItem(position: Int): Any {
        return listPersonas[position]
    }

    /**
     * obtener el id del item
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Retorna la vista con los elementos
     */
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, vista: View?, parent: ViewGroup?): View {
        val v:View
        val inflater:LayoutInflater=LayoutInflater.from(context)
        v = inflater.inflate(R.layout.elementopersona,null)
        val txtNombre : TextView=v.findViewById(R.id.txtNombre)
        txtNombre.text = listPersonas[position].nombre
        val txtTelefono : TextView=v.findViewById(R.id.txtTelefono)
        txtTelefono.text = listPersonas[position].celular
        val imgFoto : ImageView=v.findViewById(R.id.imgFoto)
        imgFoto.setImageResource(listPersonas[position].foto)
        imgFoto.adjustViewBounds = true
        imgFoto.scaleType = ImageView.ScaleType.FIT_CENTER
        return v
    }
}