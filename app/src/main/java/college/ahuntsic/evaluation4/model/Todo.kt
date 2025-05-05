package college.ahuntsic.evaluation4.model

import java.time.LocalDate

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

data class Todo (
    val id : Int,
    val dateCreation : LocalDate,
    val name: String,
    val note : String,
    val priority: Priority,
    val completed : Boolean,
    val endDate : LocalDate
)


