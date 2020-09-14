package cl.grupo2.impoglobal.productos.domain

import io.reactivex.Single

import cl.grupo2.impoglobal.productos.domain.model.Producto

interface ProductosRepository {
     suspend fun getAll() : List<Producto>
    /* fun obtenerProductos() : Single <List<Producto>>*/

}