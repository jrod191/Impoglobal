package cl.grupo2.impoglobal.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.FragmentProductosBinding
import cl.grupo2.impoglobal.network.RetrofitHandler
import cl.grupo2.impoglobal.productos.data.remote.RemoteProductosRepository
//import cl.grupo2.impoglobal.productos.data.remote.ProductoMapper
import cl.grupo2.impoglobal.productos.domain.ObtenerProductosUseCase
import cl.grupo2.impoglobal.productos.domain.model.Producto
import cl.grupo2.impoglobal.productos.presentation.ProductosState
import cl.grupo2.impoglobal.productos.presentation.ProductosViewModel
import cl.grupo2.impoglobal.productos.presentation.ProductosViewModelFactory

class ProductosFragment : Fragment(R.layout.fragment_productos), OnItemClickProducto {

    private lateinit var viewModel: ProductosViewModel
    private lateinit var viewModelFactory: ProductosViewModelFactory
    private lateinit var binding: FragmentProductosBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentProductosBinding.bind(view)
        setupRecyclerView()
        setupLiveData()
        //setupUseCase()
    }

    private fun setupDependencies() {
        viewModelFactory = ProductosViewModelFactory(
            ObtenerProductosUseCase(
                RemoteProductosRepository(
                    RetrofitHandler.getProductosApi()
                )
            )
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductosViewModel::class.java)


    }

    private fun setupRecyclerView() {
        binding.apply {
            rvProductos.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(context)
            rvProductos.layoutManager = layoutManager
            rvProductos.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer {
                it?.let { safeState -> handleState(safeState) }
            }
        )
        viewModel.obtenerProductos()
    }

    private fun handleState(safeState: ProductosState) {
        when (safeState) {
            is ProductosState.LoadingProductosState -> showLoading()
            is ProductosState.ErrorServerProductosState -> showErrorServer()
            is ProductosState.Success -> loadResult(safeState.result)
            is ProductosState.EmptyListProductosState -> showEmptyList()
        }
    }

    private fun showEmptyList() {
        Toast.makeText(context, "Empty List", Toast.LENGTH_SHORT).show()
    }

    private fun loadResult(producto: List<Producto>?) {
        producto?.let { safeProducto ->
            binding.rvProductos.adapter = ProductoAdapter(safeProducto, this)
        }
    }

    private fun showErrorServer() {
        Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        Toast.makeText(context, "Cargando", Toast.LENGTH_SHORT).show()
    }

    override fun onItemCLickProducto(producto: Producto) {

    }
}