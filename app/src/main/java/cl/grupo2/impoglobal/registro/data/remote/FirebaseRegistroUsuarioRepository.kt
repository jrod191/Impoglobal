package cl.grupo2.impoglobal.registro.data.remote

import cl.grupo2.impoglobal.registro.domain.RegistroUsuario
import cl.grupo2.impoglobal.registro.domain.RegistroUsuarioRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import com.google.firebase.database.FirebaseDatabase

class FirebaseRegistroUsuarioRepository(

    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : RegistroUsuarioRepository{

    override suspend fun registro(registroUsuario: RegistroUsuario): Boolean {
        val result = creaUsuarioEnFirebase(registroUsuario.email, registroUsuario.password)
        agregarNombreAFirebase(registroUsuario.nombre)
        agregarDatosPersonalesAFirebase(
            registroUsuario.nombre,
            registroUsuario.rut,
            registroUsuario.email,
            registroUsuario.direccion,
            registroUsuario.comuna
        )
        return result.user?.email?.isNotEmpty() ?: false
    }

    private suspend fun creaUsuarioEnFirebase(email: String, password: String): AuthResult {
        return firebaseAuth
            .createUserWithEmailAndPassword(email, password)
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

    private suspend fun agregarDatosPersonalesAFirebase(
        nombre: String,
        rut: String,
        email: String,
        direccion:String,
        comuna:String
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
    }


}