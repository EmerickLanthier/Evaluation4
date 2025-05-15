package college.ahuntsic.evaluation4.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import college.ahuntsic.evaluation4.model.Priority

@Composable
fun SecondPage(
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
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete button"
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var nom by remember { mutableStateOf("") }
            OutlinedTextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text("Nom de la tache") }
            )
            var note by remember { mutableStateOf("") }
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Ajouter une note") }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 65.dp, start = 65.dp)
            ) {
                var selectedDate by remember { mutableStateOf<Long?>(null) }
                DatePickerFieldToModal(selectedDate, onDateSelected = { selected ->
                    selectedDate = selected
                    selected
                })
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                var priority by remember { mutableStateOf(Priority.LOW) }
                Text(priority.toString())
                MinimalDropdownMenu(priority, { priority = it })
            }
            Button(onClick = { toEcranAccueil() }) {
                Text(text = "Enregistrer")
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
                text = { Text("Low") },
                onClick = {
                    onPrioritySelected(Priority.LOW)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Medium") },
                onClick = {
                    onPrioritySelected(Priority.MEDIUM)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Hight") },
                onClick = {
                    onPrioritySelected(Priority.HIGH)
                    expanded = false
                }
            )
        }
    }
}