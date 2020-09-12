package cl.grupo2.impoglobal.productos.domain

class ObtenerProductosUseCase (
    private val productosRepository: ProductosRepository
) {
   fun execute() = productosRepository.obtenerProductos()
}
