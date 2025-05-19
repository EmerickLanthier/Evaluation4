package college.ahuntsic.evaluation4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

@Entity(tableName = "task")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val dateCreation : String,
    val name: String,
    val note : String,
    val priority: Priority,
    val completed : Boolean,
    val endDate : String
)


