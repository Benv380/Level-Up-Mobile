package cl.duoc.level_up_mobile.model

data class Recordatorio(
    val id: Long = 0L,
    val uid: String,
    val createdAt: String,
    val message: String
)
