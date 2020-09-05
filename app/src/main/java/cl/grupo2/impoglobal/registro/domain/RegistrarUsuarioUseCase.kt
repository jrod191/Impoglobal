package cl.grupo2.impoglobal.registro.domain

class RegistrarUsuarioUseCase(private val registroUsuarioRepository : RegistroUsuarioRepository) {

    suspend fun execute(registroUsuario: RegistroUsuario) = registroUsuarioRepository.registro(registroUsuario)
}