package college.ahuntsic.evaluation4.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import college.ahuntsic.evaluation4.model.Todo

@Composable
fun TodoList(
    todoList: List<Todo>,
    modifier: Modifier,
    onDelete: (todo:Todo)->Unit,
    onTodoCheck: (todo: Todo, check: Boolean) -> Unit
) {
    var expandedTodo by remember { mutableStateOf<String?>(null) }
    LazyColumn(modifier = modifier) {
        items(todoList, key = {it.name}) {  todo ->
            TodoCard(
                todo,
                expandedTodo == todo.name,
                modifier = Modifier.padding(8.dp),
                {onDelete(todo)},
                { td ->
                    expandedTodo = if (td.name == expandedTodo) null else td.name
                }) {
                    check ->
                onTodoCheck(todo, check)
            }
        }
    }
}