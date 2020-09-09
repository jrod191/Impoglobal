package cl.grupo2.impoglobal.productos.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.grupo2.impoglobal.productos.domain.ObtenerProductosUseCase
import cl.grupo2.impoglobal.productos.domain.Productos
import cl.grupo2.impoglobal.productos.presentation.ProductosState.LoadingProductosState
import cl.grupo2.impoglobal.productos.presentation.ProductosState.LoadProductosState
import cl.grupo2.impoglobal.productos.presentation.ProductosState.EmptyListProductosState
import kotlinx.coroutines.launch
import java.net.CacheResponse

class ProductosViewModel (
    private val obtenerProductosUseCase: ObtenerProductosUseCase
) : ViewModel(){

    private val liveData = MutableLiveData<ProductosState>()

    fun getLiveData() = liveData

    fun obtenerProductos(){
        liveData.postValue(LoadingProductosState)
        viewModelScope.launch {
            val response = obtenerProductosUseCase.execute()
            handleResponse(response)
        }
    }

    private fun handleResponse(response: Productos){
        response.result.let {
            if(it.isNotEmpty()){
                liveData.postValue(LoadProductosState(response))
            }else{
                liveData.postValue(EmptyListProductosState)
            }
        }
    }
}