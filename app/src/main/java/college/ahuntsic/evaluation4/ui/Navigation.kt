package college.ahuntsic.evaluation4.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import college.ahuntsic.evaluation4.home.EcranAccueil
import college.ahuntsic.evaluation4.model.Todo
import college.ahuntsic.evaluation4.model.TodoViewModel

@Composable
fun MainNav(
    viewModel: TodoViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "accueil"
    ) {
        composable("accueil") {
            EcranAccueil(
                toSecondPage = {
                    navController.navigate("secondPage")
                },
                toModifier = {todo -> navController.navigate(route = "secondPage/${todo.id}") {
                    launchSingleTop = true
                } }
            )
        }
        composable("secondPage/{idTodo}",
            arguments = listOf(navArgument("idTodo") { type = NavType.IntType })) {
            backStackEntry ->
            val idTodo = backStackEntry.arguments?.getInt("idTodo") ?: -1
            SecondPage(
                idTodo = idTodo,
                toEcranAccueil = { navController.navigate("accueil") {launchSingleTop = true}},
                onBack = { navController.navigateUp() }
            )
        }
        composable("secondPage") {
            SecondPage(
                idTodo = -1,
                toEcranAccueil = { navController.navigate("accueil") {launchSingleTop = true}},
                onBack = { navController.navigateUp() }
            )
        }
    }
}