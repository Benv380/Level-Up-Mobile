package  cl.duoc.level_up_mobile.ui.vmfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duoc.level_up_mobile.ui.recordatorio.RecordatorioViewModel
import data.local.AppDatabase
import repository.auth.RecordatorioRepository



class RecordatorioVMFactory(
    context: Context,
    private val uid: String
) : ViewModelProvider.Factory {

    private val repo by lazy {
        val db = AppDatabase.get(context)
        RecordatorioRepository(db.reminderDao())
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecordatorioViewModel(repo, uid) as T
    }
}
