package college.ahuntsic.evaluation4.data

import college.ahuntsic.evaluation4.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodoStream(): Flow<List<Todo>>

    fun getTodoStream(id: Int): Flow<Todo?>

    suspend fun insertTodo(task: Todo)

    suspend fun deleteTodo(task: Todo)

    suspend fun updateTodo(task: Todo)
}