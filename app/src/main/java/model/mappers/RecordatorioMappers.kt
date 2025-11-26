package cl.duoc.level_up_mobile.model.mappers

import cl.duoc.level_up_mobile.model.Recordatorio
import data.local.RecordatorioEntity

fun RecordatorioEntity.toDto() = Recordatorio(
    id = id,
    uid = uid,
    createdAt = createdAt,
    message = message
)

fun Recordatorio.toEntity() = RecordatorioEntity(
    id = id,
    uid = uid,
    createdAt = createdAt,
    message = message
)
