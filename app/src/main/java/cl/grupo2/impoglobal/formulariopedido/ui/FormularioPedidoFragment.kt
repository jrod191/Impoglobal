package cl.grupo2.impoglobal.formulariopedido.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.FragmentFormularioBinding
import cl.grupo2.impoglobal.databinding.FragmentRegistroUsuarioBinding
import cl.grupo2.impoglobal.formulariopedido.data.remote.FirebaseFormularioPedidoRepository
import cl.grupo2.impoglobal.formulariopedido.data.remote.FirebaseFormularioPedidoRepository
import cl.grupo2.impoglobal.formulariopedido.domain.FormularioPedidoUseCase
import cl.grupo2.impoglobal.formulariopedido.presentation.FormularioPedidoViewModel
import cl.grupo2.impoglobal.formulariopedido.presentation.FormularioViewModelFactory
import cl.grupo2.impoglobal.registro.data.remote.FirebaseRegistroUsuarioRepository

import cl.grupo2.impoglobal.registro.utils.extensions.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FormularioPedidoFragment : Fragment (R.layout.fragment_formulario){


    lateinit var binding: FragmentFormularioBinding
    lateinit var viewModel: FormularioPedidoViewModel
    lateinit var viewModelFactory: FormularioViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentFormularioBinding.bind(view)
        setupLiveData()
        setupListener()
    }

    private fun setupDependencies(){
        viewModelFactory =
            FormularioViewModelFactory(
                FormularioPedidoUseCase(
                    FirebaseFormularioPedidoRepository(
                        FirebaseAuth.getInstance(),
                        FirebaseDatabase.getInstance()
                    )
                )
            )
        viewModel =
            ViewModelProvider(this,viewModelFactory).get(FormularioPedidoViewModel::class.java)
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
        alert("Eror servidor")
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


