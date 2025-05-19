package college.ahuntsic.evaluation4.data

import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.model.Priority
import java.time.LocalDate

object Database {
    fun loadTodos(): List<Todo> {
        return listOf(
            Todo(1, LocalDate.now().toString(), "Sortir le chien","Faire attention", Priority.MEDIUM, false, LocalDate.of(2025,6,24).toString()),
            Todo(2, LocalDate.now().toString(),"Faire exercice Android","bien faire les devoirs", Priority.HIGH, false, LocalDate.of(2025,6,25).toString()),
            Todo(3, LocalDate.now().toString(), "Appeler ma maman","Demander des nouvelles",Priority.LOW, false, LocalDate.of(2025,6,26).toString()),
            Todo(4, LocalDate.now().toString(), "Allez au cinéma","Pas acheter de sucreries",Priority.LOW, false, LocalDate.of(2025,6,28).toString()),
            Todo(5, LocalDate.now().toString(), "Checker Tiktok","Pas plus de 30min",Priority.HIGH, false, LocalDate.of(2025,6,27).toString())
        )
    }
}