package cl.grupo2.impoglobal.productos.data.remote

import cl.grupo2.impoglobal.productos.domain.Productos
import cl.grupo2.impoglobal.productos.domain.ProductosRepository
import io.reactivex.Single

class RemoteProductosRepository (
    private val productosApi: ProductosApi,
    private val productoMapper: ProductoMapper) : ProductosRepository {

    override fun obtenerProductos(): List<ProductoModel> {
        val productos = productosApi.getProductos()

        val listaCompleta=
            productos.map { productoMapper.mapToEntity(it) } ?: emptyList()

        return productosApi.getProductos()
    }
}