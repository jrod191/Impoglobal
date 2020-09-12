package cl.grupo2.impoglobal.productos.domain

import io.reactivex.Single

interface ProductosRepository {
     fun obtenerProductos() : Single <List<Productos>>
}