package mnxk.kotlintex.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import mnxk.kotlintex.mywishlistapp.data.Wish
import mnxk.kotlintex.mywishlistapp.data.WishRepository
import mnxk.kotlintex.mywishlistapp.data.WishRepository.*

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository,
) : ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChange(wishTitle: String) {
        wishTitleState = wishTitle
    }

    fun onWishDescriptionChange(wishDescription: String) {
        wishDescriptionState = wishDescription
    }

    // Lateinit is used to tell the compiler that the variable will be initialized later
    // then we can use the variable without initializing it.
    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish = wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish = wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish = wish)
        }
    }
}
