package mnxk.kotlintex.mywishlistapp

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import mnxk.kotlintex.mywishlistapp.data.Wish
import androidx.compose.material.Icon as Icon

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel,
) {
    val context = LocalContext.current
    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            AppBarView(title = "WishList", {
                Toast.makeText(
                    context,
                    "Back button clicked",
                    Toast.LENGTH_SHORT,
                ).show()
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 16.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = { // Todo add navigation to the screen}) {
                    Toast.makeText(
                        context,
                        "Addind an item to the wishlist",
                        Toast.LENGTH_SHORT,
                    ).show()
                    navController.navigate(Screen.AddScreen.route + "/0L")
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
    ) {
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it),
        ) {
            items(wishlist.value, key = { wish -> wish.id }) {
                    wish ->
                val dismissState =
                    rememberDismissState(
                        confirmValueChange = {
                            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                viewModel.deleteWish(wish)
                            }
                            true
                        },
                    )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val context = LocalContext.current
                        val dismissColor = ContextCompat.getColor(context, R.color.app_bar_color)
                        val color by animateColorAsState(
                            if (dismissState.dismissDirection ==
                                androidx.compose.material3
                                    .DismissDirection.EndToStart || dismissState.dismissDirection
                                == DismissDirection.StartToEnd
                            ) {
                                Color(dismissColor)
                            } else {
                                Color.Transparent
                            },
                            label = "",
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                            contentAlignment = alignment,
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White,
                            )
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun WishItem(
    wish: Wish,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .clickable {
                    onClick()
                },
        elevation = 10.dp,
        backgroundColor = Color.White,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}
