package modelo

class Categoria(id: Int, var nombre: String) {
    var id = id
    override fun toString(): String {
        return nombre
    }
}