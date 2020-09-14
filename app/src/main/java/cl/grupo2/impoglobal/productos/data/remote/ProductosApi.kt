package cl.grupo2.impoglobal.productos.data.remote

import cl.grupo2.impoglobal.productos.domain.model.Producto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ProductosApi {

    @GET("productos")
    suspend fun getProductosApi(): List<Producto>

}