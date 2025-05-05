package college.ahuntsic.evaluation4.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import college.ahuntsic.evaluation4.home.EcranAccueil

@Composable
fun MainNav(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "accueil"
    ){
        composable ("accueil"){
            EcranAccueil(
                toSecondPage = {
                    navController.navigate("secondPage"){
                        launchSingleTop = true
                    }
                },
                onBack = {navController.navigateUp()}
            )
        }
    }
}