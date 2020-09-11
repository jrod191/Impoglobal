package cl.grupo2.impoglobal.productos.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.FragmentProductosBinding
import cl.grupo2.impoglobal.network.RetrofitHandler
import cl.grupo2.impoglobal.productos.data.remote.ProductoMapper
import cl.grupo2.impoglobal.productos.data.remote.RemoteProductosRepository
import cl.grupo2.impoglobal.productos.domain.ObtenerProductosUseCase
import cl.grupo2.impoglobal.productos.domain.Productos

class ProductosFragment : Fragment(R.layout.fragment_productos) {

    private lateinit var binding: FragmentProductosBinding
    private lateinit var viewModel: ProductosViewModel
    private lateinit var viewModelFactory: ProductosViewModelFactory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentProductosBinding.bind(view)
        setupLiveData()
    }

    private fun setupDependencies(){
        viewModelFactory = ProductosViewModelFactory(
            ObtenerProductosUseCase(
                RemoteProductosRepository(
                    RetrofitHandler.getProductosApi(),
                    ProductoMapper()
                )
            )
        )

        viewModel = ViewModelProvider(this,viewModelFactory).get(ProductosViewModel::class.java)


    }

    private fun setupLiveData () {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state -> state?.let { handleState(it) } }
        )
        viewModel.obtenerProductos()
    }

    private fun handleState(state: ProductosState) {
        when (state){
            is ProductosState.LoadingProductosState -> showLoading()
            is ProductosState.LoadProductosState -> loadProductos(state.result)
            is ProductosState.EmptyListProductosState ->showEmptyList()
            is ProductosState.ErrorServerProductosState ->showErrorServer()
            is ProductosState.NotInternetProductosState -> showSinInternetMessage()
        }
    }

    private fun showSinInternetMessage(){

    }

    private fun showErrorServer(){

    }

    private fun showEmptyList(){

    }

    private fun showLoading(){

    }

    private fun loadProductos(result: Productos){
        Toast.makeText(context,"result ${result.result.size}", Toast.LENGTH_SHORT).show()

    }


}