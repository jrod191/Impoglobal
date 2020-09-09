package cl.grupo2.impoglobal.formulariopedido.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.impoglobal.formulariopedido.domain.FormularioPedidoUseCase


class FormularioViewModelFactory (
    private val formularioPedidoUseCase: FormularioPedidoUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FormularioPedidoUseCase::class.java)
            .newInstance(formularioPedidoUseCase)
    }
}
