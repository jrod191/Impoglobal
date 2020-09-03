package cl.grupo2.impoglobal.autenticacion.data.remote

import cl.grupo2.impoglobal.autenticacion.domain.AutenticacionRepository
import cl.grupo2.impoglobal.autenticacion.domain.UserAuth
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAutenticacionRepository (
    private val firebaseAuth: FirebaseAuth
) : AutenticacionRepository{

    override suspend fun doLogin(email: String, password: String): UserAuth {
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
        val user = firebaseAuth.currentUser
        return UserAuth(user?.displayName ?: "", user?.email?:"")
    }
}