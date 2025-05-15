package college.ahuntsic.evaluation4.data

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import college.ahuntsic.evaluation4.model.Todo

@Dao
interface TodoDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task:Todo)

    @Update
    suspend fun update(task: Todo)

    @Delete
    suspend fun delete(task: Todo)

    @Query("SELECT * from task WHERE id = :id")
    fun getTodo(id: Int): Flow<Todo>

    @Query("SELECT * from task ORDER BY id ASC")
    fun getAllTodo(): Flow<List<Todo>>
}