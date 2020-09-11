package cl.grupo2.impoglobal.formulariopedido.data.remote

import cl.grupo2.impoglobal.formulariopedido.domain.FormularioPedido
import cl.grupo2.impoglobal.formulariopedido.domain.FormularioPedidoRepository
import cl.grupo2.impoglobal.registro.data.remote.RegistroUsuarioFirebase

import com.google.firebase.auth.AuthResult

import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseFormularioPedidoRepository (

    private val firebaseDatabase: FirebaseDatabase
) :FormularioPedidoRepository {

    override suspend fun formulario(formularioPedido: FormularioPedido): Boolean {

        val database = firebaseDatabase.getReference("pedidos/${formularioPedido.solicitud}")

        database.setValue(formularioPedido).await()

        return true

    }




    /*private val firebaseDatabase: FirebaseDatabase
) : FormularioPedidoRepository {

    override suspend fun formulario(formularioPedido: FormularioPedido): Boolean {
        val result = ingresarPedidoEnFirebase(formularioPedido.solicitud, formularioPedido.cantidad, formularioPedido.monto, formularioPedido.caracteristicas, formularioPedido.imagen) {
            guardarPedidoEnFirebase(formularioPedido.solicitud, formularioPedido.cantidad, formularioPedido.monto,formularioPedido.caracteristicas, formularioPedido.imagen)
            return@ingresarPedidoEnFirebase
            }

            val database = firebaseDatabase.getReference("pedidos/${solicitud}")
            val ingresarPedidoEnFirebase = IngresarPedidoEnFirebase(
                solicitud,
                cantidad,
                monto,
                caracteristicas,
                imagen
            )
            database.setValue(ingresarPedidoEnFirebase).await()
        }

        private suspend fun ingresarPedidoEnFirebase


        }
        agregarSolicitudAFirebase(formularioPedido.solicitud)
        agregarInformacionPedidoAFirebase(
            formularioPedido.solicitud,
            formularioPedido.cantidad,
            formularioPedido.monto,
            formularioPedido.caracteristicas,
            formularioPedido.imagen
        )
        return result.solicitud?.cantidad?.isNotEmpty() ?: false
    }

    private suspend fun ingresarPedidoEnFirebase(solicitud: String, cantidad: String): AuthResult {
        return firebaseDatabase
            .crear(solicitud, cantidad)
            .await()
    }

    private suspend fun agregarNombreAFirebase(
        nombre: String
    ){
        val user = firebaseAuth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(nombre)
            .build()
        user?.updateProfile(profileUpdates)?.await()
    }

    private suspend fun agregarInformacionPedidoAFirebase(
        solicitud: String,
        cantidad: String,
        monto: Int,
        caracteristicas:String,
        imagen:String
    ){
        val dataBase = firebaseDatabase.getReference("usuarios/${rut}")
        val registroUsuarioFirebase = RegistroUsuarioFirebase(
            nombre,
            rut,
            email,
            direccion,
            comuna
        )
        dataBase.setValue(registroUsuarioFirebase).await()
    }*/


}
