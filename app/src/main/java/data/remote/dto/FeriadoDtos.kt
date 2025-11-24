package cl.duoc.level_up_mobile.data.remote.dto

data class FeriadoResponse(
    val status: String,
    val data: List<FeriadoDto>
)

data class FeriadoDto(
    val date: String,
    val title: String,
    val type: String,
    val inalienable: Boolean,
    val extra: String?
)
