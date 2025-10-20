package data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tabla: recordatorios
 * Requisitos: uid, fecha creación (dd/MM/yyyy), mensaje
 */
@Entity(tableName = "recordatorios")
data class RecordatorioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val uid: String,
    val createdAt: String,
    val message: String
)
