package cl.grupo2.impoglobal.productos.data.remote

import cl.grupo2.impoglobal.productos.domain.Producto

class ProductoMapper {

    fun  mapToEntity(productoModel: ProductoModel): Producto{
         productoModel.apply {
            return Producto(id, nombre, valor, codigo)
        }
    }
}

