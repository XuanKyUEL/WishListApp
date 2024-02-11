package mnxk.kotlintex.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import mnxk.kotlintex.mywishlistapp.R.color
import mnxk.kotlintex.mywishlistapp.R.string.add_wish
import mnxk.kotlintex.mywishlistapp.R.string.update_wish
import mnxk.kotlintex.mywishlistapp.data.Wish

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController,
) {
    val snackMessage =
        remember {
            mutableStateOf("")
        }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0L) {
        // TODO Get wish by id
        val wish =
            viewModel.getWishById(id)
                .collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(
                title =
                    if (id != 0L) {
                        stringResource(id = update_wish)
                    } else {
                        stringResource(id = add_wish)
                    },
            ) { navController.navigateUp() }
        },
    ) {
        Column(
            modifier =
                Modifier
                    .padding(it)
                    .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChange = { viewModel.onWishTitleChange(it) },
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChange = { viewModel.onWishDescriptionChange(it) },
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (viewModel.wishTitleState.isNotEmpty() &&
                        viewModel.wishDescriptionState.isNotEmpty()
                    ) {
                        if (id != 0L) {
                            // TODO Update wish
                        } else {
                            // TODO Add wish
                            viewModel.addWish(
                                Wish(
                                    title = viewModel.wishTitleState.trim(),
                                    description = viewModel.wishDescriptionState.trim(),
                                ),
                            )
                            snackMessage.value = "Wish added successfully"
                        }
                    } else {
                        // Enter all the fields
                        snackMessage.value = "Enter all the fields to add a wish"
                    }
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }
                },
                colors =
                    ButtonDefaults.buttonColors(
                        colorResource(id = color.app_bar_color),
                    ),
            ) {
                Text(
                    text =
                        if (id != 0L) {
                            stringResource(id = update_wish)
                        } else {
                            stringResource(
                                id = add_wish,
                            )
                        },
                    style =
                        TextStyle(
                            fontSize = 18.sp,
                            color = Color.White,
                        ),
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors =
            TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = colorResource(id = color.black),
                unfocusedBorderColor = colorResource(id = color.black),
                cursorColor = colorResource(id = color.black),
                unfocusedLabelColor = colorResource(id = color.black),
            ),
    )
}

@Preview
@Composable
fun WishTestFieldPrev() {
    WishTextField(
        label = "Check",
        value = "Check",
        onValueChange = {},
    )
}
