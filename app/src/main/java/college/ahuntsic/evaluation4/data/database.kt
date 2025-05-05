package college.ahuntsic.evaluation4.data

import android.os.Build
import androidx.annotation.RequiresApi
import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.model.Priority
import java.time.LocalDate

object Database {
    fun loadTodos(): List<Todo> {
        return listOf(
            Todo(1, LocalDate.now(), "Sortir le chien","Faire attention", Priority.MEDIUM, false, LocalDate.of(2025,6,24)),
            Todo(2, LocalDate.now(), "Faire exercice Android","bien faire les devoirs", Priority.HIGH, false, LocalDate.of(2025,6,25)),
            Todo(3, LocalDate.now(), "Appeler ma maman","Demander des nouvelles",Priority.LOW, false, LocalDate.of(2025,6,26)),
            Todo(4, LocalDate.now(), "Allez au cinéma","Pas acheter de sucreries",Priority.LOW, false, LocalDate.of(2025,6,28)),
            Todo(5, LocalDate.now(), "Checker Tiktok","Pas plus de 30min",Priority.HIGH, false, LocalDate.of(2025,6,27))
        )
    }
}