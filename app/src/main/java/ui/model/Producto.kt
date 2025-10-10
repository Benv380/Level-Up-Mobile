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
            titulo = "Catan",
            precio = "29.990 CLP",
            imagenRes = R.drawable.catan,
            categoria = "Juegos de Mesa",
            descripcion = "Un clásico juego de estrategia donde los jugadores compiten por colonizar y expandirse en la isla de Catan. Ideal para 3-4 jugadores y perfecto para noches de juego en familia o con amigos."
        ),
        Producto(
        id = 2,
        titulo = "Carcassonne",
        precio = "24.990 CLP",
        imagenRes = R.drawable.carcasonne,
        categoria = "Juegos de Mesa",
        descripcion = "Un juego de colocación de fichas donde los jugadores construyen el paisaje alrededor de la fortaleza medieval de Carcassonne. Ideal para 2-5 jugadores y fácil de aprender."
        ),
        Producto(
        id = 3,
        titulo = "Controlador Inalámbrico Xbox Series X",
        precio = "59.990 CLP",
        imagenRes = R.drawable.controladorxbox,
        categoria = "Accesorios",
        descripcion = "Ofrece una experiencia de juego cómoda con botones mapeables y una respuesta táctil mejorada. Compatible con consolas Xbox y PC."
        ),
        Producto(
        id = 4,
        titulo = "Auriculares Gamer HyperX Cloud II",
        precio = "79.990 CLP",
        imagenRes = R.drawable.audifonos,
        categoria = "Accesorios",
        descripcion = "Proporcionan un sonido envolvente de calidad con un micrófono desmontable y almohadillas de espuma viscoelástica para mayor comodidad durante largas sesiones de juego."
        ),
        Producto(
        id = 5,
        titulo = "PlayStation 5",
        precio = "549.990 CLP",
        imagenRes = R.drawable.playstation5,
        categoria = "Consolas",
        descripcion = "La consola de última generación de Sony, que ofrece gráficos impresionantes y tiempos de carga ultrarrápidos para una experiencia de juego inmersiva."
        ),
        Producto(
        id = 6,
        titulo = "PC Gamer ASUS ROG Strix",
        precio = "1.299.990 CLP",
        imagenRes = R.drawable.computadorasus,
        categoria = "Computadores Gamers",
        descripcion = "Un potente equipo diseñado para los gamers más exigentes, equipado con los últimos componentes para ofrecer un rendimiento excepcional en cualquier juego."
        ),
        Producto(
        id = 7,
        titulo = "Silla Gamer Secretlab Titan",
        precio = "349.990 CLP",
        imagenRes = R.drawable.sillagamer,
        categoria = "Sillas Gamers",
        descripcion = "Diseñada para el máximo confort, esta silla ofrece un soporte ergonómico y personalización ajustable para sesiones de juego prolongadas."
        ),
        Producto(
        id = 8,
        titulo = "Mouse Gamer Logitech G502 HERO",
        precio = "49.990 CLP",
        imagenRes = R.drawable.mousegamer,
        categoria = "Mouse",
        descripcion = "Con sensor de alta precisión y botones personalizables, este mouse es ideal para gamers que buscan un control preciso y personalización."
        ),
        Producto(
        id = 9,
        titulo = "Mousepad Razer Goliathus Extended Chroma",
        precio = "29.990 CLP",
        imagenRes = R.drawable.mousepaggamer,
        categoria = "Mousepad",
        descripcion = "Ofrece un área de juego amplia con iluminación RGB personalizable, asegurando una superficie suave y uniforme para el movimiento del mouse."
        ),
        Producto(
        id = 10,
        titulo = "Polera Gamer Personalizada 'Level-Up'",
        precio = "14.990 CLP",
        imagenRes = R.drawable.poleralevelup,
        categoria = "Poleras Personalizadas",
        descripcion = "Una camiseta cómoda y estilizada, con la posibilidad de personalizarla con tu gamer tag o diseño favorito."
        )

)
