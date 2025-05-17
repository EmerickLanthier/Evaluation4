package college.ahuntsic.evaluation4.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import college.ahuntsic.evaluation4.data.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    val allTodos: StateFlow<List<Todo>> = todoRepository.getAllTodoStream()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }

    fun getTodo(id: Int): StateFlow<Todo?> {
        return todoRepository.getTodoStream(id)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }
}