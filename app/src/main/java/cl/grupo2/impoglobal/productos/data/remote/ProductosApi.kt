package cl.grupo2.impoglobal.productos.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ProductosApi {

    @GET("productos")
    fun getProductos(): List<ProductoModel>

}