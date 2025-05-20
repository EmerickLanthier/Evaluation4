package college.ahuntsic.evaluation4.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import college.ahuntsic.evaluation4.data.AppDataContainer
import college.ahuntsic.evaluation4.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoRepository = AppDataContainer(application).todoRepository

    val allTodos: StateFlow<List<Todo>> = todoRepository.getAllTodoStream()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedTodo = MutableStateFlow<Todo?>(null)
    val selectedTodo = _selectedTodo.asStateFlow()

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

    fun getTodo(id: Int) {
        viewModelScope.launch {
            todoRepository.getTodoStream(id).collectLatest {
                _selectedTodo.value = it
            }
        }
    }
}