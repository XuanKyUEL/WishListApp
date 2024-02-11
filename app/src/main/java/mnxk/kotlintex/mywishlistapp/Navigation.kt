package mnxk.kotlintex.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
        composable(
            Screen.AddScreen.route + "/{wishId}",
            arguments =
                listOf(
                    navArgument("wishId") {
                        type = NavType.LongType
                        defaultValue = 0L
                        nullable = false
                    },
                ),
        ) {
                entry ->
            val wishId =
                if (entry.arguments != null) {
                    entry.arguments!!.getLong("wishId")
                } else {
                    0L
                }
            AddEditDetailView(
                id = wishId,
                viewModel = viewModel,
                navController = navController,
            )
        }
    }
}
