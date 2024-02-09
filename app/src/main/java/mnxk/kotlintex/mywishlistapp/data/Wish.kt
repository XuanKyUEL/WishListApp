package mnxk.kotlintex.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish_table")
data class Wish(
    @Suppress("ktlint:standard:value-parameter-comment")
    @PrimaryKey(autoGenerate = true) // This will auto-generate the id for each wish
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
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
