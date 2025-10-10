package cl.duoc.level_up_mobile.ui.model

import androidx.annotation.DrawableRes
import cl.duoc.level_up_mobile.R


data class Producto(
    val id: Int,
    val titulo: String,
    val precio: String,
    @DrawableRes val imagenRes: Int,
    val categoria: String,
    val descripcion: String
)

val productosDemo = listOf(
    Producto(
        id = 1,
        titulo = "Bombones de chocolate",
        precio = "1.500 (1kg)",
        imagenRes = R.drawable.bonbonchocolate,
        categoria = "Dulces",
        descripcion = "Aca se describe el producto"
    ),
    Producto(
        id = 2,
        titulo = "Pastel de chocolate",
        precio = "20.000",
        imagenRes = R.drawable.tortachocolate,
        categoria = "Pasteles",
        descripcion = "Aca se describe el producto"
    ),
    Producto(
        id = 3,
        titulo = "Pie de limón",
        precio = "18.000",
        imagenRes = R.drawable.pielimon,
        categoria = "Pasteles",
        descripcion = "Aca se describe el producto"
    ),
    Producto(
        id = 4,
        titulo = "Bombones de chocolate 2",
        precio = "3.500 (1kg)",
        imagenRes = R.drawable.bonbonchocolate,
        categoria = "Dulces",
        descripcion = "Aca se describe el producto"
    ),
    Producto(
        id = 5,
        titulo = "Pastel de chocolate 2",
        precio = "25.000",
        imagenRes = R.drawable.tortachocolate,
        categoria = "Pasteles",
        descripcion = "Aca se describe el producto"
    ),
    Producto(
        id = 6,
        titulo = "Pie de limón 2",
        precio = "20.000",
        imagenRes = R.drawable.pielimon,
        categoria = "Pasteles",
        descripcion = "Aca se describe el producto"
    ),
)
