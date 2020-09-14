package cl.grupo2.impoglobal.recyclerview

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.CardviewBinding
import cl.grupo2.impoglobal.productos.domain.model.Producto
import okhttp3.internal.notifyAll

class ProductosVH(
    private val binding: CardviewBinding,
    private val onItemClickProducto: OnItemClickProducto
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(producto: Producto) {
        binding.apply {
            tvNombre.text = producto.nombre
            if (producto.nombre.isNotEmpty()) {
            }
        }
        onItemClickProducto.onItemCLickProducto(producto)
    }
}


