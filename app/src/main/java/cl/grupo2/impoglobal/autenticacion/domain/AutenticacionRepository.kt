package cl.grupo2.impoglobal.autenticacion.domain

interface AutenticacionRepository {
    suspend fun doLogin(email: String, clave: String) : UserAuth

}