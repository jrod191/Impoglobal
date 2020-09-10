package cl.grupo2.impoglobal.contactanos.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.FragmentContactanosBinding

class ContactanosFragment : Fragment (R.layout.fragment_contactanos){

    private lateinit var binding: FragmentContactanosBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentContactanosBinding.bind(view)
        setupListeners()
    }

    private fun setupListeners(){
        binding.apply {

            btnFinalizar.setOnClickListener {

            }
        }
    }


}