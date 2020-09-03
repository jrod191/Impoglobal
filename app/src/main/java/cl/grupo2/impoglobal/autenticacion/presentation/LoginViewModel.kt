package cl.grupo2.impoglobal.autenticacion.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.grupo2.impoglobal.autenticacion.domain.LoginUsuarioPassUseCase
import cl.grupo2.impoglobal.autenticacion.domain.UserAuth
import kotlinx.coroutines.launch

class LoginViewModel (
    private val loginUsuarioPassUseCase: LoginUsuarioPassUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<LoginUiState>()

    fun getLiveData() = liveData

    fun doLogin (email: String, password: String) {
        liveData.postValue(LoginUiState.Loading)
        viewModelScope.launch {
            try {
                val result = loginUsuarioPassUseCase.execute(email, password)
                handleResult(result)
            } catch (exception: Exception){
                handleError(exception)
            }
        }
    }

    private fun handleResult(result: UserAuth){
        liveData.postValue(LoginUiState.Success(result))
    }

    private fun handleError(exception: Throwable){
        liveData.postValue(LoginUiState.Error(exception))
    }
}