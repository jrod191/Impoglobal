package cl.grupo2.impoglobal.autenticacion.domain

class LoginUsuarioPassUseCase (
    private val repository: AutenticacionRepository
){
    suspend fun execute (email: String, clave: String) = repository.doLogin(email,clave)
}