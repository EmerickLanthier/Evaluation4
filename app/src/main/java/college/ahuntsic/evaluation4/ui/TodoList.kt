package college.ahuntsic.evaluation4.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.model.TodoViewModel

@Composable
fun TodoList(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier,
    toSecondPage: (todo: Todo) -> Unit
) {
    var expandedTodoId by remember { mutableStateOf<Int?>(null) }
    val todos by viewModel.filteredTodos.collectAsState()

    LazyColumn(modifier = modifier) {
        items(todos, key = { it.id }) { todo ->
            TodoCard(
                todo = todo,
                ouvert = expandedTodoId == todo.id,
                modifier = Modifier.padding(8.dp),
                onDelete = { viewModel.deleteTodo(todo) },
                onExpand = { expandedTodoId = if (expandedTodoId == todo.id) null else todo.id },
                onCheck = { isChecked ->
                    viewModel.updateTodo(todo.copy(completed = isChecked))
                },
                onEdit = { toSecondPage(todo) }

            )
        }
    }
}