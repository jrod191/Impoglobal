package cl.grupo2.impoglobal.menu.presentation

sealed class MenuUiState (
    open val result: Boolean? = null,
    open val error: Throwable? = null
){


}