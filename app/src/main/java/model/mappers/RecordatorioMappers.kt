package cl.duoc.level_up_mobile.model.mappers

import cl.duoc.level_up_mobile.data.local.RecordatorioEntity
import cl.duoc.level_up_mobile.model.Recordatorio

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
