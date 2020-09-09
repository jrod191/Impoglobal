package cl.grupo2.impoglobal.productos.data.remote

import cl.grupo2.impoglobal.productos.domain.Productos
import cl.grupo2.impoglobal.productos.domain.ProductosRepository

class RemoteProductosRepository (
    private val productosApi: ProductosApi,
    private val productoMapper: ProductoMapper
) : ProductosRepository {

    override suspend fun obtenerProductos(): Productos {
        val productos = productosApi.getProductos()

        val listaCompleta=
            productos.productos?.map { productoMapper.mapToEntity(it) } ?: emptyList()

        return Productos(listaCompleta)
    }
}