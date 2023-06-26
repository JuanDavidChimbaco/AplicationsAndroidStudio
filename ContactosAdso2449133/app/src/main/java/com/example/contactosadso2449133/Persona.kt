package com.example.contactosadso2449133

class Persona {
    lateinit  var nombre : String
    lateinit var celular : String
    var foto: Int = 0

    constructor(nombre:String,celular:String,foto:Int){
        this.nombre = nombre
        this.celular = celular
        this.foto = foto
    }
}