package college.ahuntsic.evaluation4.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import college.ahuntsic.evaluation4.home.EcranAccueil
import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.model.TodoViewModel

@Composable
fun MainNav(
    viewModel: TodoViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "accueil"
    ) {
        composable("accueil") {
            EcranAccueil(
                viewModel = viewModel,
                toSecondPage = { todo ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("todo", todo)
                    navController.navigate("secondPage")
                }
            )
        }
        composable("secondPage") {
            val todo = navController.previousBackStackEntry?.savedStateHandle?.get<Todo>("todo")
            SecondPage(
                viewModel = viewModel,
                todo = todo,
                toEcranAccueil = { navController.navigate("accueil") { popUpTo("accueil") { inclusive = false } } },
                onBack = { navController.navigateUp() }
            )
        }
    }
}