package cl.grupo2.impoglobal.autenticacion.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.autenticacion.data.remote.FirebaseAutenticacionRepository
import cl.grupo2.impoglobal.autenticacion.domain.LoginUsuarioPassUseCase
import cl.grupo2.impoglobal.autenticacion.domain.UserAuth
import cl.grupo2.impoglobal.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login){

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginUsuarioPassUseCase: LoginUsuarioPassUseCase
    private lateinit var repository: FirebaseAutenticacionRepository
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginViewModelFactory: LoginViewModelFactory



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentLoginBinding.bind(view)
        setupLiveData()
        setupListeners()
    }

    private fun setupLiveData(){
        loginViewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                handleState(state)
            }
        )
    }

    private fun handleState(state: LoginUiState?) {
        when (state) {
            is LoginUiState.Loading -> mostrarCargando()
            is LoginUiState.ContrasenaIncorrecta -> mostrarErrorContrasena()
            is LoginUiState.Success -> mostrarLoginCorrecto(state.result)
            is LoginUiState.Error -> mostrarError()
        }
    }

    private fun mostrarError(){
        Toast.makeText(requireContext(), "Ha ocurrido un error", Toast.LENGTH_LONG).show()
    }

    private fun mostrarLoginCorrecto(result: UserAuth?) {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_menuFragment)
    }

    private fun mostrarErrorContrasena() {
        Toast.makeText(requireContext(), "Contraseña Incorrecta", Toast.LENGTH_LONG).show()
    }

    private fun mostrarCargando (){
        Toast.makeText(requireContext(),"Cargando", Toast.LENGTH_SHORT).show()
    }

    private fun setupDependencies(){
        repository = FirebaseAutenticacionRepository(FirebaseAuth.getInstance())
        loginUsuarioPassUseCase = LoginUsuarioPassUseCase(repository)
        loginViewModelFactory = LoginViewModelFactory (loginUsuarioPassUseCase)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    private fun setupListeners() {
        binding.apply {
            btnIngresar.setOnClickListener {
                val email = etEmail.text.toString()
                val clave = etClave.text.toString()
                callLoginViewModel(email, clave)
            }

            btnCrearUsuario.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registrarUsuarioFragment)
            }
        }
    }

    private fun callLoginViewModel(email: String, clave: String){
    loginViewModel.doLogin(email, clave)
    }

}