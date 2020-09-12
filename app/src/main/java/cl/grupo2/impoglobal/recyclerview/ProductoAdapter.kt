package cl.grupo2.impoglobal.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.productos.domain.Producto
import cl.grupo2.impoglobal.productos.domain.Productos
import cl.grupo2.impoglobal.productos.presentation.ProductosFragment

class ProductoAdapter(
    private val producto: List<Producto>,
    private val listener: ProductosFragment
) : RecyclerView.Adapter<ProductosVH> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cardview, parent, false)
        return ProductosVH(view, listener)
    }

    override fun getItemCount() = producto.size

    override fun onBindViewHolder(holder: ProductosVH, position: Int) {
        holder.bind(producto[position])
    }

    }


