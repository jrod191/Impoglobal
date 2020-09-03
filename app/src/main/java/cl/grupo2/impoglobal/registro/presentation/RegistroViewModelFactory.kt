package cl.grupo2.impoglobal.registro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.impoglobal.registro.domain.RegistrarUsuarioUseCase

class RegistroViewModelFactory (
    private val registrarUsuarioUseCase: RegistrarUsuarioUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegistrarUsuarioUseCase::class.java)
            .newInstance(registrarUsuarioUseCase)
    }
}