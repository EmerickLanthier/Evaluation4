package college.ahuntsic.evaluation4.data

import college.ahuntsic.evaluation4.model.Todo
import kotlinx.coroutines.flow.Flow

class OfflineTodoRepository(private val todoDao: TodoDao) : TodoRepository {
    override fun getAllTodoStream(): Flow<List<Todo>> = todoDao.getAllTodo()

    override fun getTodoStream(id: Int): Flow<Todo?> = todoDao.getTodo(id)

    override suspend fun insertTodo(task: Todo) = todoDao.insert(task)

    override suspend fun deleteTodo(task: Todo) = todoDao.delete(task)

    override suspend fun updateTodo(task: Todo) = todoDao.update(task)
}