package cl.grupo2.impoglobal.productos.domain

class ObtenerProductosUseCase (
    private val repository: ProductosRepository
) {
    suspend fun execute() = repository.getAll()
   /*fun execute() = productosRepository.obtenerProductos()*/
}
