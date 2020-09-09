package cl.grupo2.impoglobal.formulariopedido.domain


interface FormularioPedidoRepository {

    suspend fun formulario(formularioPedido: FormularioPedido) : Boolean
}