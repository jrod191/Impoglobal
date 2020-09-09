package cl.grupo2.impoglobal.productos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.impoglobal.productos.domain.ObtenerProductosUseCase

class ProductosViewModelFactory (
    private val obtenerProductosUseCase: ObtenerProductosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ObtenerProductosUseCase::class.java)
            .newInstance(obtenerProductosUseCase)
    }


}