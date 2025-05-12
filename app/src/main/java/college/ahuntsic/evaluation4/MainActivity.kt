package college.ahuntsic.evaluation4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import college.ahuntsic.evaluation4.data.Database
import college.ahuntsic.evaluation4.home.EcranAccueil
import college.ahuntsic.evaluation4.ui.MainNav
import college.ahuntsic.evaluation4.ui.ToDoBar
import college.ahuntsic.evaluation4.ui.TodoList
import college.ahuntsic.evaluation4.ui.theme.Evaluation4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Evaluation4Theme {
                MainNav()
            }
        }
    }
}
