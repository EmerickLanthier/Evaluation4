package college.ahuntsic.evaluation4.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import college.ahuntsic.evaluation4.data.TodoRepository
import java.time.LocalDate

class TodoEntryViewModel(private val todoRepository: TodoRepository) : ViewModel() {
    var todoUiState by mutableStateOf(TodoUiState())
        private set

    fun updateUiState(todoDetails: TodoDetails) {
        todoUiState =
            TodoUiState(todoDetails = todoDetails, isEntryValid = validateInput(todoDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            todoRepository.insertTodo(todoUiState.todoDetails.toTodo())
        }
    }

    private fun validateInput(uiState: TodoDetails = todoUiState.todoDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && note.isNotBlank() && endDate.toString().isNotBlank()
        }
    }
}

data class TodoUiState(
    val todoDetails: TodoDetails = TodoDetails(),
    val isEntryValid: Boolean = false
)

data class TodoDetails(
    val id: Int = 0,
    val dateCreation: LocalDate = LocalDate.now(),
    val name: String = "",
    val note: String = "",
    val priority: Priority = Priority.LOW,
    val completed: Boolean = false,
    val endDate: LocalDate = LocalDate.now()
)

fun TodoDetails.toTodo(): Todo = Todo(
    id = id,
    dateCreation = dateCreation,
    name = name,
    note = note,
    priority = priority,
    completed = completed,
    endDate = endDate
)

fun Todo.toTodoUiState(isEntryValid: Boolean = false): TodoUiState = TodoUiState(
    todoDetails = this.toTodoDetails(),
    isEntryValid = isEntryValid
)

fun Todo.toTodoDetails(): TodoDetails = TodoDetails(
    id = id,
    dateCreation = dateCreation,
    name = name,
    note = note,
    priority = priority,
    completed = completed,
    endDate = endDate
)