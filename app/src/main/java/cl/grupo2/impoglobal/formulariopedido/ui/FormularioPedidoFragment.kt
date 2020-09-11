package cl.grupo2.impoglobal.formulariopedido.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.FragmentFormularioBinding
import cl.grupo2.impoglobal.formulariopedido.data.remote.FirebaseFormularioPedidoRepository
import cl.grupo2.impoglobal.formulariopedido.domain.FormularioPedido
import cl.grupo2.impoglobal.formulariopedido.domain.FormularioPedidoUseCase
import cl.grupo2.impoglobal.formulariopedido.presentation.FormularioPedidoViewModel
import cl.grupo2.impoglobal.formulariopedido.presentation.FormularioViewModelFactory

import cl.grupo2.impoglobal.registro.utils.extensions.*
import com.google.firebase.database.FirebaseDatabase

class FormularioPedidoFragment : Fragment (R.layout.fragment_formulario){


    lateinit var binding: FragmentFormularioBinding
    lateinit var viewModel: FormularioPedidoViewModel
    lateinit var viewModelFactory: FormularioViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentFormularioBinding.bind(view)
        //setupLiveData()
        setupListener()
    }

    private fun setupDependencies(){
        viewModelFactory =
            FormularioViewModelFactory(
                FormularioPedidoUseCase(
                    FirebaseFormularioPedidoRepository(
                        FirebaseDatabase.getInstance()
                    )
                )
            )
        viewModel =
            ViewModelProvider(this,viewModelFactory).get(FormularioPedidoViewModel::class.java)
    }

    /*private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                state?.let { handleState(it) }*/
           // }
        //)
   // }

    private fun setupListener() {
        binding.apply {
            btnIngresarSolicitud.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_formularioPedidoFragment_to_contactanosFragment)
                    //viewModel.registrarUsuario(obtenerValoresDeEditText())

            }

            btnVolver.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    /*private fun isAllValidInputs(): Boolean {
        binding.apply {
            return etSolicitud.isValidNameInput("Ingrese una solicitud valida") ||
                    etCantidadAPedir.isValidNumberInput("Ingrese cantidad valida")
                    /*etMontoEnPesos.isValidNumberInput("Ingrese monto valido") ||
                    etDetallesProductos.isValidNameInput("Ingrese un nombre valido") ||
                    etLink.isValidNameInput("Ingrese una comuna valida")*/

        }
    }

    private fun obtenerValoresDeEditText(): FormularioPedido {
        binding.apply {
            return FormularioPedido(
                etSolicitud.text.toString(),
                etCantidadAPedir.text.toString()
                /*etMontoEnPesos.text.toString(),
                etDetallesProductos.text.toString(),
                etLink.text.toString()*/

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
    }*/


}


