package cl.grupo2.impoglobal.autenticacion.presentation

import cl.grupo2.impoglobal.autenticacion.domain.UserAuth

sealed class LoginUiState (
    open val result : UserAuth? = null,
    open val error: Throwable? = null
){
    object Loading : LoginUiState()
    object ContrasenaIncorrecta : LoginUiState()
    data class Success (override val result: UserAuth?) : LoginUiState(result = result)
    data class Error (override val error: Throwable?) : LoginUiState(error = error)

}