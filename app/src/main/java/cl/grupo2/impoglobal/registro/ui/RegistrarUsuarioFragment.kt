package cl.grupo2.impoglobal.registro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.FragmentRegistroUsuarioBinding
import cl.grupo2.impoglobal.registro.data.remote.FirebaseRegistroUsuarioRepository
import cl.grupo2.impoglobal.registro.domain.RegistrarUsuarioUseCase
import cl.grupo2.impoglobal.registro.domain.RegistroUsuario
import cl.grupo2.impoglobal.registro.presentation.RegistroUiState
import cl.grupo2.impoglobal.registro.presentation.RegistroUsuarioViewModel
import cl.grupo2.impoglobal.registro.presentation.RegistroViewModelFactory
import cl.grupo2.impoglobal.registro.utils.extensions.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registro_usuario.*

class RegistrarUsuarioFragment : Fragment (R.layout.fragment_registro_usuario) {

    lateinit var binding: FragmentRegistroUsuarioBinding
    lateinit var viewModel: RegistroUsuarioViewModel
    lateinit var viewModelFactory: RegistroViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentRegistroUsuarioBinding.bind(view)
        setupLiveData()
        setupListener()
    }

    private fun setupDependencies(){
        viewModelFactory =
            RegistroViewModelFactory(
                RegistrarUsuarioUseCase(
                    FirebaseRegistroUsuarioRepository(
                        FirebaseAuth.getInstance(),
                        FirebaseDatabase.getInstance()
                    )
                )
            )
        viewModel =
            ViewModelProvider(this,viewModelFactory).get(RegistroUsuarioViewModel::class.java)
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                state?.let { handleState(it) }
            }
        )
    }

    private fun setupListener() {
        binding.apply {
            btnCrearUsuario1.setOnClickListener {
                if (isAllValidInputs()) {
                    viewModel.registrarUsuario(obtenerValoresDeEditText())
                }
            }

            btnVolver.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun isAllValidInputs(): Boolean {
        binding.apply {
            return etPass.isValidPassInput("Ingrese contraseña con 6 caracteres") ||
                    etEmail.isValidEmailInput("Ingrese un correo valido") ||
                    etRut.isValidRutInput("Ingrese un rut valido") ||
                    etNombre.isValidNameInput("Ingrese un nombre valido") ||
                    etComuna.isValidComunaInput("Ingrese una comuna valida") ||
                    etDireccion.isValidDireccionInput("Ingrese una direccion valida")
        }
    }

    private fun obtenerValoresDeEditText(): RegistroUsuario {
        binding.apply {
            return RegistroUsuario(
                etNombre.text.toString(),
                etRut.text.toString(),
                etDireccion.text.toString(),
                etComuna.text.toString(),
                etEmail.text.toString(),
                etPass.text.toString()
            )
        }
    }

    private fun handleState(state: RegistroUiState){
        when (state) {
            is RegistroUiState.LoadingRegistroUiState -> showLoading()
            is RegistroUiState.SuccessRegistroUiState -> showRegistroExitoso()
            is RegistroUiState.InvalidEmailRegistroUiState -> showEmailRegistrado()
            is RegistroUiState.ErrorRegistroUiState -> showError()
        }
    }

    private fun showError() {
        alert("Error de servidor")
    }

    private fun showEmailRegistrado(){
        alert("Email ya registrado")
        binding.etEmail.requestFocus()
    }

    private fun showRegistroExitoso(){
        alert("Registro Exitoso")
    }

    private fun showLoading(){
        alert("Cargando")
    }


}