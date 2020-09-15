package cl.grupo2.impoglobal.productos.data.remote

import com.google.gson.annotations.SerializedName

data class ProductoModel (

    @SerializedName ("id") val id: Int,
    @SerializedName  ("Nombre") val nombre: String,
    @SerializedName  ("Valor") val valor: String,
    @SerializedName  ("Codigo") val codigo: String
)
