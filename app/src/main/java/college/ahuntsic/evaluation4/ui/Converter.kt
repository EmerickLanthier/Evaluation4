package college.ahuntsic.evaluation4.ui

import androidx.room.TypeConverter
import college.ahuntsic.evaluation4.model.Priority

class Converters {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(value: String): Priority {
        return Priority.valueOf(value)
    }
}