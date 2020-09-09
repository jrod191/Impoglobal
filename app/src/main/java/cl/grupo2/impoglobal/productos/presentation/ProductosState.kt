package cl.grupo2.impoglobal.productos.presentation

import cl.grupo2.impoglobal.productos.domain.Productos

sealed class ProductosState (
    open val result: Productos? = null
){
    object LoadingProductosState: ProductosState()
    data class LoadProductosState (override val result: Productos): ProductosState(result = result)
    object EmptyListProductosState: ProductosState()
    object ErrorServerProductosState: ProductosState()
    object NotInternetProductosState: ProductosState()
}