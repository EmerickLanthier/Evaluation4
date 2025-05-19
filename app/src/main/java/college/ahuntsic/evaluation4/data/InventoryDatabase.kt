package college.ahuntsic.evaluation4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.ui.Converters

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@androidx.room.TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "todo_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}