package cl.grupo2.impoglobal.registro.domain

interface RegistroUsuarioRepository {

    suspend fun registro(registroUsuario: RegistroUsuario) : Boolean
}