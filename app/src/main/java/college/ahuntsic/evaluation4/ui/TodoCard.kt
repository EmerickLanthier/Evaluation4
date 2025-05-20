package college.ahuntsic.evaluation4.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.spring
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import college.ahuntsic.evaluation4.R
import college.ahuntsic.evaluation4.model.Priority
import college.ahuntsic.evaluation4.model.Todo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun TodoCard(
    todo: Todo,
    ouvert: Boolean,
    modifier: Modifier = Modifier,
    onDelete: (todo: Todo) -> Unit,
    onExpand: (todo: Todo) -> Unit,
    onCheck: (checked: Boolean) -> Unit,
    onEdit: (todo: Todo) -> Unit
) {
    val resIconId = when (todo.priority) {
        Priority.HIGH -> R.drawable.baseline_check_24
        Priority.MEDIUM -> R.drawable.baseline_check_circle_outline_24
        else -> R.drawable.baseline_check_circle_24
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (ouvert) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer
    )

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(start = 10.dp, bottom = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(resIconId),
                    contentDescription = "Priority Icon",
                    modifier = Modifier.padding(end = 10.dp)
                )

                Text(text = todo.dateCreation.toString(), modifier = Modifier.weight(1f))

                IconButton(onClick = { onEdit(todo) }) {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Delete button"
                    )
                }
                Checkbox(
                    checked = todo.completed,
                    onCheckedChange = { onCheck(!todo.completed) }
                )

            }
            Spacer(Modifier.padding(5.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = todo.name, modifier = Modifier.weight(1f))
                UnBouton(ouvert, { onExpand(todo) })
            }
            if (ouvert) {
                Spacer(Modifier.padding(5.dp))
                Text(text = todo.endDate.toString())
                Spacer(Modifier.padding(5.dp))
                Text(text = todo.note)
            }
        }
    }
}

@Composable
fun UnBouton(
    ouvert: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (ouvert) Icons.Filled.ExpandLess else Icons
                .Filled.ExpandMore,
            contentDescription = "Ouvrir",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}