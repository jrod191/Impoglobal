package cl.grupo2.impoglobal.registro.domain

data class RegistroUsuario (
    val nombre: String,
    val rut: String,
    val direccion: String,
    val comuna: String,
    val email: String,
    val password: String
)
