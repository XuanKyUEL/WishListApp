package mnxk.kotlintex.mywishlistapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeView() {
    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            AppBarView(title = "My Wish List")
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
        ) {
        }
    }
}
