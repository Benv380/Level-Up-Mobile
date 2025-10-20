package data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("SELECT * FROM recordatorios WHERE uid = :uid ORDER BY id DESC")
    fun observeByUid(uid: String): Flow<List<RecordatorioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: RecordatorioEntity): Long

    @Update
    suspend fun update(reminder: RecordatorioEntity)

    @Delete
    suspend fun delete(reminder: RecordatorioEntity)

    @Query("SELECT * FROM recordatorios WHERE id = :id")
    suspend fun findById(id: Long): RecordatorioEntity?
}
