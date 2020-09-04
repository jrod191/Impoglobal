package cl.grupo2.impoglobal.autenticacion.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.autenticacion.domain.AutenticacionRepository
import cl.grupo2.impoglobal.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login){

    private lateinit var repository: AutenticacionRepository
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
    }


}