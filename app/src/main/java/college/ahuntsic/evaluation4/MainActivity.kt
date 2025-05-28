package college.ahuntsic.evaluation4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import college.ahuntsic.evaluation4.ui.MainNav
import college.ahuntsic.evaluation4.ui.theme.Evaluation4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Evaluation4Theme{
                MainNav()
            }
        }
    }
}