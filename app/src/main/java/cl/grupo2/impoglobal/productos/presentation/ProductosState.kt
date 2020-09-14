package cl.grupo2.impoglobal.productos.presentation

import cl.grupo2.impoglobal.productos.domain.Productos
import cl.grupo2.impoglobal.productos.domain.model.Producto

sealed class ProductosState (
    open val result: List <Producto>? = null
){
    object LoadingProductosState: ProductosState()
    object EmptyListProductosState: ProductosState()
    data class Success (override val result: List<Producto>): ProductosState(result)
    object ErrorServerProductosState: ProductosState()
    //object NotInternetProductosState: ProductosState()
}