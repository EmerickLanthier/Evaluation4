package college.ahuntsic.evaluation4.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import college.ahuntsic.evaluation4.data.Database
import college.ahuntsic.evaluation4.ui.TodoList
import college.ahuntsic.evaluation4.R
import college.ahuntsic.evaluation4.ui.ToDoBar

@Composable
fun EcranAccueil(
    toSecondPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ToDoBar({ Text("", maxLines = 1, overflow = TextOverflow.Ellipsis) })
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
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
            Icon(Icons.Filled.Add, "Floating action button")
        }
    }
        }
}