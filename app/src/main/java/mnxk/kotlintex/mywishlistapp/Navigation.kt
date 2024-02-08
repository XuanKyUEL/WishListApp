package mnxk.kotlintex.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigatioon(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeView(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(Screen.AddScreen.route) {
            AddEditDetailView(
                id = 0L,
                viewModel = viewModel,
                navController = navController,
            )
        }
    }
}
