package cl.grupo2.impoglobal.autenticacion.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.grupo2.impoglobal.autenticacion.domain.LoginUsuarioPassUseCase
import cl.grupo2.impoglobal.autenticacion.domain.UserAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.launch

class LoginViewModel (private val loginUsuarioPassUseCase: LoginUsuarioPassUseCase) : ViewModel() {

    private val liveData = MutableLiveData<LoginUiState>()

    fun getLiveData() = liveData

    fun doLogin (email: String, password: String) {
        liveData.postValue(LoginUiState.Loading)
        viewModelScope.launch {
            try {
                val result = loginUsuarioPassUseCase.execute(email, password)
                handleResult(result)
            }catch (invalidCredentials: FirebaseAuthInvalidCredentialsException) {
                liveData.postValue(LoginUiState.ContrasenaIncorrecta)
            } catch (exception: Exception){
                liveData.postValue(LoginUiState.Error(exception))
            }
        }
    }

    private fun handleError(error: Throwable ?){
        liveData.postValue(LoginUiState.Error(error))

    }

    private fun handleResult(result: UserAuth){
        if(result.nombre.isNotEmpty()){
            liveData.postValue(LoginUiState.Success(result))
        }else{
        liveData.postValue(LoginUiState.ContrasenaIncorrecta)
     }
    }
}