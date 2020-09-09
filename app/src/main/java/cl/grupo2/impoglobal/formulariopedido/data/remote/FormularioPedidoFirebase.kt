package cl.grupo2.impoglobal.formulariopedido.data.remote

data class FormularioPedidoFirebase (

    val solicitud: String,
    val cantidad: String,
    val monto: Int,
    val caracteristicas: String,
    val imagen: String
)
