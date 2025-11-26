package cl.duoc.level_up_mobile.ui.model.mappers

import cl.duoc.level_up_mobile.data.remote.dto.ProductoDto
import model.Producto

fun ProductoDto.toUi(): Producto {
    return Producto(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        precio = precio,
        imagen = nombre // si después agregas lógica para imágenes locales
    )
}
