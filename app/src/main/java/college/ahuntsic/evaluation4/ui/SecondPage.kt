package college.ahuntsic.evaluation4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import college.ahuntsic.evaluation4.R
import college.ahuntsic.evaluation4.model.Priority
import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.model.TodoViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun SecondPage(
    viewModel: TodoViewModel,
    todo: Todo? = null,
    toEcranAccueil: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            ToDoBar(
                { Text("", maxLines = 1, overflow = TextOverflow.Ellipsis) }, {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back arrow"
                        )
                    }
                },
                {
                    if (todo != null) {
                        IconButton(onClick = { viewModel.deleteTodo(todo); toEcranAccueil() }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete button"
                            )
                        }
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Demo background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var selectedDate by remember { mutableStateOf<Long?>(null) }
            var nom by remember { mutableStateOf("") }
            var note by remember { mutableStateOf("") }
            var priority by remember { mutableStateOf(Priority.LOW) }
            LaunchedEffect(todo) {
                if (todo != null) {
                    nom = todo.name
                    note = todo.note
                    priority = todo.priority
                    selectedDate = LocalDate.parse(todo.endDate)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli()
                }
            }
            OutlinedTextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text(stringResource(R.string.nom_de_la_tache)) }
            )

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text(stringResource(R.string.ajouter_une_note)) }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 65.dp, start = 65.dp)
            ) {
                DatePickerFieldToModal(selectedDate, onDateSelected = { selected ->
                    selectedDate = selected
                    selected
                })
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(priority.toString())
                MinimalDropdownMenu(priority, { priority = it })
            }
            Button(
                onClick = {
                   if (nom.isNotBlank() && selectedDate != null) {
                        val endDateStr = selectedDate?.let {
                            Instant.ofEpochMilli(it)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .toString()
                        } ?: ""
                        val newTodo = Todo(
                            id = todo?.id ?: 0,
                            dateCreation = todo?.dateCreation ?: LocalDate.now().toString(),
                            name = nom,
                            note = note,
                            priority = priority,
                            completed = todo?.completed ?: false,
                           endDate = endDateStr
                        )
                        if (todo != null) {
                            viewModel.updateTodo(newTodo)
                        } else {
                            viewModel.insertTodo(newTodo)
                        }
                        toEcranAccueil()
                    }
                },
               enabled = nom.isNotBlank() && selectedDate != null
            ) {
                Text(text = if (todo != null) stringResource(R.string.mettre_jour) else stringResource(R.string.enregistrer))
            }

        }
    }
}


@Composable
fun MinimalDropdownMenu(
    currentPriority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.petite)) },
                onClick = {
                    onPrioritySelected(Priority.LOW)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.moyenne)) },
                onClick = {
                    onPrioritySelected(Priority.MEDIUM)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.grande)) },
                onClick = {
                    onPrioritySelected(Priority.HIGH)
                    expanded = false
                }
            )
        }
    }
}