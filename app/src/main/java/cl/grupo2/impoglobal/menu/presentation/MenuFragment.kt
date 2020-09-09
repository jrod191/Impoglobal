package cl.grupo2.impoglobal.menu.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.FragmentLoginBinding
import cl.grupo2.impoglobal.databinding.FragmentMenuBinding
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : Fragment (R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMenuBinding.bind(view)
        setupListeners()
    }

    private fun setupListeners() {
        binding.apply {

            //btnCatalogo.setOnClickListener {
            //Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_menuFragment)
             //}
            btnPedidoPersonalizado.setOnClickListener {
              Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_formularioPedidoFragment)
            }

            btnVolver.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

}

