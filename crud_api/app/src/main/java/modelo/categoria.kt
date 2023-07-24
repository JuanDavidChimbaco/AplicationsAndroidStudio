package modelo

class Categoria(id: String, var nombre: String) {
    var id = id
    override fun toString(): String {
        return nombre
    }
}