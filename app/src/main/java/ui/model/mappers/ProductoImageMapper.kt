package ui.model.mappers

import cl.duoc.level_up_mobile.R
import model.Producto

fun Producto.toImageRes(): Int {
    return when (this.id?.toInt() ?: -1) {
        1 -> R.drawable.catan
        2 -> R.drawable.carcasonne
        3 -> R.drawable.controladorxbox
        4 -> R.drawable.audifonos
        5 -> R.drawable.playstation5
        6 -> R.drawable.computadorasus
        7 -> R.drawable.sillagamer
        8 -> R.drawable.mousegamer
        9 -> R.drawable.mousepaggamer
        10 -> R.drawable.poleralevelup
        else -> R.drawable.catalogo.takeIf { it != 0 } ?: R.drawable.ic_launcher_background
    }
}

