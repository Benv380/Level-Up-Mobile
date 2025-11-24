package cl.duoc.level_up_mobile.model

data class Feriado(
    val date: String,
    val title: String,
    val type: String,
    val inalienable: Boolean,
    val extra: String?
)
