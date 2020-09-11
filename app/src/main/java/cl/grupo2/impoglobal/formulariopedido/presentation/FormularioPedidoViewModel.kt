package cl.grupo2.impoglobal.formulariopedido.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.grupo2.impoglobal.formulariopedido.domain.FormularioPedidoUseCase

class FormularioPedidoViewModel (
    private val formularioPedidoUseCase: FormularioPedidoUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<FormularioUiState>()

    fun getLiveData() = liveData

}