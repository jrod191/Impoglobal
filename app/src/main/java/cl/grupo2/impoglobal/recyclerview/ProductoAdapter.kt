package cl.grupo2.impoglobal.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.CardviewBinding
import cl.grupo2.impoglobal.productos.domain.model.Producto

class ProductoAdapter(
    private val producto: List<Producto>,
    private val itemClickProducto: OnItemClickProducto
) : RecyclerView.Adapter<ProductosVH> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosVH {
        val inflate = LayoutInflater.from(parent.context)
        val binding = CardviewBinding.inflate(inflate,parent, false)
        return ProductosVH(binding, itemClickProducto)
    }

    override fun onBindViewHolder(holder: ProductosVH, position: Int) {
        holder.bind(producto[position])
    }

    override fun getItemCount() = producto.size

    }


