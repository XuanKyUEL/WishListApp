package mnxk.kotlintex.mywishlistapp.data

data class Wish(
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
)

object DummyWish {
    val wishList =
        listOf(
            Wish(
                title = "New Phone",
                description = "Upgrade to the latest model",
            ),
            Wish(
                title = "New Laptop",
                description = "Lenovo Legion Pro 5",
            ),
            Wish(
                title = "New Watch",
                description = "Apple Watch Series 7",
            ),
        )
}
