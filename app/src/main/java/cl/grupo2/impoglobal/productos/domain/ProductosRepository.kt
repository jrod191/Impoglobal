package cl.grupo2.impoglobal.productos.domain

interface ProductosRepository {
    suspend fun obtenerProductos() : Productos
}