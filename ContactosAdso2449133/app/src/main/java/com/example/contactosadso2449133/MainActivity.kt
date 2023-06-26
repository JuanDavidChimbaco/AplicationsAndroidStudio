package com.example.contactosadso2449133

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Crear la variable listview con el elemento creado en el layout
        val listPersonas:ListView=findViewById(R.id.listaPersonas)
        //crear una valriable vacia tipo streing para las personas
        val personas = mutableListOf<Persona>()

        //agrego elementos a la lista:
        val per1 = Persona("Pedro Marmol","3172917179",R.drawable.img_3)
        val per2 = Persona("Monica Galindo","3168887495", R.drawable.img_1)
        val per3 = Persona("Angie Cepeda","3115987415", R.drawable.img_4)

        personas.add(per1)
        personas.add(per2)
        personas.add(per3)
        personas.add(per1)
        personas.add(per2)
        personas.add(per3)
        personas.add(per1)
        personas.add(per2)
        personas.add(per3)
        personas.add(per1)
        personas.add(per2)
        personas.add(per3)

        //Crear adaptador con la clase creada
        val adaptor = Adaptor(this,R.layout.elementopersona,personas)

        //indicarle a la lista cual es el adaptador
        listPersonas.adapter = adaptor

        //respuesta al evento click en un item de la lista
        listPersonas.setOnItemClickListener{ _, _, posicion, _ ->
            Toast.makeText(this@MainActivity, "Click en la posicion $posicion, con Nombre: "+personas[posicion].nombre, Toast.LENGTH_SHORT).show()
        }
    }
}