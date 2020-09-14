package cl.grupo2.impoglobal.productos.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.grupo2.impoglobal.productos.domain.ObtenerProductosUseCase
import cl.grupo2.impoglobal.productos.domain.Productos
import cl.grupo2.impoglobal.productos.domain.model.Producto
import cl.grupo2.impoglobal.productos.presentation.ProductosState.LoadingProductosState
import cl.grupo2.impoglobal.productos.presentation.ProductosState.EmptyListProductosState
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.CacheResponse

class ProductosViewModel (
    private val obtenerProductosUseCase: ObtenerProductosUseCase
) : ViewModel(){

    private val liveData = MutableLiveData<ProductosState>()

    fun getLiveData() = liveData

    fun obtenerProductos(){
        liveData.postValue(ProductosState.LoadingProductosState)
        viewModelScope.launch {
            try {
            val result = obtenerProductosUseCase.execute()
            handleResult(result)
        }catch (e: Exception){
            handleError(e)
            }
        }
    }

    private fun handleError(e: Exception) {
        liveData.postValue(ProductosState.ErrorServerProductosState)
    }

     private fun handleResult(result: List<Producto>) {
        if(result.isEmpty()){
                liveData.postValue(ProductosState.EmptyListProductosState)
            }else{
                liveData.postValue(ProductosState.Success(result))
            }
        }
    }
