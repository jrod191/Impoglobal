package cl.grupo2.impoglobal.productos.data.remote

import com.google.gson.annotations.SerializedName

data class ProductoModel (

    @SerializedName ("id") val id: Int,
    @SerializedName  ("nombre") val nombre: String,
    @SerializedName  ("valor") val valor: Int,
    @SerializedName  ("codigo") val codigo: String
)
