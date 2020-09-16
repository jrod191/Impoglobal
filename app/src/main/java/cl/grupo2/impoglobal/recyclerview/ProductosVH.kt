package cl.grupo2.impoglobal.recyclerview

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.databinding.ItemProductosBinding
import cl.grupo2.impoglobal.productos.domain.model.Producto
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import okhttp3.internal.notifyAll

class ProductosVH(
    private val binding: ItemProductosBinding,
    private val onItemClickProducto: OnItemClickProducto
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(producto: Producto) {
        binding.apply {
            tvNombre.text = producto.nombre
            tvValor.text = producto.valor
            tvCodigo.text = producto.codigo
            if (producto.imagen.isNotEmpty()) {
                Picasso.get().load(producto.imagen).into(ivProducto)

            }
        }
        onItemClickProducto.onItemCLickProducto(producto)
    }
}


