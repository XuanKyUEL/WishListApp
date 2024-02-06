package mnxk.kotlintex.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeView() {
    val context = LocalContext.current
    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            AppBarView(title = "My Wish List", {
                Toast.makeText(
                    context,
                    "Back button clicked",
                    Toast.LENGTH_SHORT,
                ).show()
            })
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
        ) {
        }
    }
}
