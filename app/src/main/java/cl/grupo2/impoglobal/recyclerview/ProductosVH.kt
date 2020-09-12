package cl.grupo2.impoglobal.recyclerview

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import cl.grupo2.impoglobal.R
import cl.grupo2.impoglobal.productos.domain.Producto
import cl.grupo2.impoglobal.productos.domain.Productos

class ProductosVH (view : View, private val productoItemListener: ProductoItemListener) :
        RecyclerView.ViewHolder(view) {

    private val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
    private val tvValor = view.findViewById<TextView>(R.id.tvValor)
    private val tvCodigo = view.findViewById<TextView>(R.id.tvCodigo)
    private val cvProductos = view.findViewById<CardView>(R.id.tvCodigo)

    fun bind (producto: Producto){
        tvNombre.text = Producto.nombre
        tvValor.text = Producto.valor
        tvCodigo.text = Producto.codigo
        cvProductos.setOnClickListener { productoItemListener?.onProductoItemClick(producto) }


    }
}


