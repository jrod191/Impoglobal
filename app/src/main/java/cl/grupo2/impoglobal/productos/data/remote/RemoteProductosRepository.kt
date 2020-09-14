package cl.grupo2.impoglobal.productos.data.remote

import cl.grupo2.impoglobal.productos.domain.ProductosRepository
import cl.grupo2.impoglobal.productos.domain.model.Producto
import io.reactivex.Single

class RemoteProductosRepository (
    private val productosApi: ProductosApi
) : ProductosRepository {

    override suspend fun getAll(): List<Producto> {
        return productosApi.getProductosApi()
    }

    /*private val productoMapper: ProductoMapper) : ProductosRepository {

    override fun obtenerProductos(): Single<List<Producto>> {
        val productos = productosApi.getProductosApi()

        val listaCompleta=
            productos.map { productoMapper.mapToEntity(it) }

        return productosApi.getProductosApi()
    }*/
}