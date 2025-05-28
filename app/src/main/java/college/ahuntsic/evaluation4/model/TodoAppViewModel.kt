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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoRepository = AppDataContainer(application).todoRepository

    // flow pr pour tous les todos
    private val _allTodos = todoRepository.getAllTodoStream()

    // flow pr requête de recherche
    private val _searchQuery = MutableStateFlow("")

    // flow pr  les todos filtrés
    val filteredTodos: StateFlow<List<Todo>> = _searchQuery
        .debounce(300) // juste und delay de 300ms
        .distinctUntilChanged()
        .combine(_allTodos) { query, todos ->
            if (query.isBlank()) {
                todos // return tous les todos si la requête est vide
            } else {
                todos.filter { todo ->
                    todo.name.contains(query, ignoreCase = true) ||
                            todo.note?.contains(query, ignoreCase = true) == true
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    private val _selectedTodo = MutableStateFlow<Todo?>(null)
    val selectedTodo = _selectedTodo.asStateFlow()

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }
    fun searchTodos(query: String) {
        _searchQuery.value = query
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