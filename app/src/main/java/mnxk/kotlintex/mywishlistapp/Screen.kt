package mnxk.kotlintex.mywishlistapp

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")

    object AddScreen : Screen("add_screen")
}
