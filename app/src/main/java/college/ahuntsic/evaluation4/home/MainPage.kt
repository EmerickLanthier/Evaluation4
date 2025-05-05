package college.ahuntsic.evaluation4.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import college.ahuntsic.evaluation4.data.Database
import college.ahuntsic.evaluation4.ui.TodoList
import college.ahuntsic.evaluation4.R

@Composable
fun EcranAccueil(
    toSecondPage: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        val todoList = remember { Database.loadTodos().toMutableStateList() }
        TodoList(todoList, modifier = modifier, { todo ->
            val index = todoList.indexOf(todo)
            todoList.removeAt(index)
        }) { todo, checked ->
            val index = todoList.indexOf(todo)
            todoList[index] = todoList[index].copy(completed = checked)
        }
        Spacer(Modifier.weight(1f))
        FloatingActionButton(onClick = toSecondPage) {
            R.drawable.baseline_add_24
        }
    }
}