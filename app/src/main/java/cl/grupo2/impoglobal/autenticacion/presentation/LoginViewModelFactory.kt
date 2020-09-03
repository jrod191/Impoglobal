package cl.grupo2.impoglobal.autenticacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.impoglobal.autenticacion.domain.LoginUsuarioPassUseCase

class LoginViewModelFactory (
    private val loginUsuarioPassUseCase: LoginUsuarioPassUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginUsuarioPassUseCase::class.java)
            .newInstance(loginUsuarioPassUseCase)
    }

}