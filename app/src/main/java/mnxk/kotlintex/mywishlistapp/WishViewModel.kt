package mnxk.kotlintex.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WishViewModel : ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChange(wishTitle: String) {
        wishTitleState = wishTitle
    }

    fun onWishDescriptionChange(wishDescription: String) {
        wishDescriptionState = wishDescription
    }
}
