package ui.repository.auth

import data.local.ReminderDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ui.model.Recordatorio
import ui.model.mappers.toDto
import ui.model.mappers.toEntity

class RecordatorioRepository(
    private val dao: ReminderDao
) {
    fun observe(uid: String): Flow<List<Recordatorio>> =
        dao.observeByUid(uid).map { list -> list.map { it.toDto() } }

    suspend fun insert(recordatorio: Recordatorio): Long = dao.insert(recordatorio.toEntity())

    suspend fun update(recordatorio: Recordatorio) = dao.update(recordatorio.toEntity())

    suspend fun delete(recordatorio: Recordatorio) = dao.delete(recordatorio.toEntity())

    suspend fun findById(id: Long): Recordatorio? = dao.findById(id)?.toDto()
}
