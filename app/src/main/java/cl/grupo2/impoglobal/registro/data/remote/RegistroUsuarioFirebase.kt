package cl.grupo2.impoglobal.registro.data.remote

import android.provider.ContactsContract

data class RegistroUsuarioFirebase (
    val nombre: String,
    val rut: String,
    val email: String,
    val direccion:String,
    val comuna: String
)
