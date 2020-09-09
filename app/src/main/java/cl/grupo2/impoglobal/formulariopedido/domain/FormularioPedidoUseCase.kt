package cl.grupo2.impoglobal.formulariopedido.domain

class FormularioPedidoUseCase (private val formularioPedidoRepository: FormularioPedidoRepository) {

    suspend fun execute(formularioPedido: FormularioPedido) = formularioPedidoRepository.formulario(formularioPedido)
}