package cl.grupo2.impoglobal.productos.domain

class ObtenerProductosUseCase (
    private val productosRepository: ProductosRepository
) {
    suspend fun execute() = productosRepository.obtenerProductos()
}
