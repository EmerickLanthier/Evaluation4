package college.ahuntsic.evaluation4.data

import android.content.Context

interface AppContainer {
    val todoRepository: TodoRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val todoRepository: TodoRepository by lazy {
        OfflineTodoRepository(InventoryDatabase.getDatabase(context).todoDao())
    }
}