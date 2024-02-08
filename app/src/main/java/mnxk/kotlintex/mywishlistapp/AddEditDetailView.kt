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
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import mnxk.kotlintex.mywishlistapp.R.string.add_wish
import mnxk.kotlintex.mywishlistapp.R.string.update_wish

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            AppBarView(
                title =
                    if (id != 0L) {
                        stringResource(id = update_wish)
                    } else {
                        stringResource(id = add_wish)
                    },
                onBackNavClick = { navController.popBackStack() },
            )
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
                        // TODO Update wish
                    } else {
                        // TODO Add wish
                    }
                },
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
                focusedBorderColor = colorResource(id = R.color.black),
                unfocusedBorderColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black),
                unfocusedLabelColor = colorResource(id = R.color.black),
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
