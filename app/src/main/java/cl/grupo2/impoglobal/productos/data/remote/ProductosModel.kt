package cl.grupo2.impoglobal.productos.data.remote

import com.google.gson.annotations.SerializedName

data class ProductosModel (
@SerializedName("results")
val productos:List<ProductoModel>?= null
)
