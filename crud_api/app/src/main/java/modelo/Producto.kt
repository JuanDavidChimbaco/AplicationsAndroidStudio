package modelo

class Producto (id:Int,codigo:Int,nombre:String,precio:Int,categoria:Categoria) {
    var id = id
    var codigo = codigo
    var nombre = nombre
    var precio = precio
    var categoria = categoria

    override fun toString(): String {
        return nombre
    }
}