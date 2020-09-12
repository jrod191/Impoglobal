package cl.grupo2.impoglobal.recyclerview

import cl.grupo2.impoglobal.productos.domain.Producto

interface ProductoItemListener {
    fun onProductoItemClick(producto:Producto)
}