package college.ahuntsic.evaluation4.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.model.TodoViewModel
import college.ahuntsic.evaluation4.ui.TodoList
import college.ahuntsic.evaluation4.ui.ToDoBar
import college.ahuntsic.evaluation4.R

@Composable
fun EcranAccueil(
    viewModel: TodoViewModel = viewModel(),
    toSecondPage: () -> Unit,
    toModifier: (todo: Todo) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ToDoBar(
                title = { Text("", maxLines = 1, overflow = TextOverflow.Ellipsis) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { toSecondPage() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajoutez")
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Demo background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Column(Modifier.padding(innerPadding)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchTodos(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Rechercher...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton({ searchQuery = "" }) {
                            Icon(Icons.Default.Close, null)
                        }
                    }
                },
                singleLine = true
            )

            TodoList(
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding),
                toSecondPage = { todo -> toModifier(todo) }

            )
        }
    }
}