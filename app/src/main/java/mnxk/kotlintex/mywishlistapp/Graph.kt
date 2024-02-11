package mnxk.kotlintex.mywishlistapp

import android.content.Context
import androidx.room.Room
import mnxk.kotlintex.mywishlistapp.data.WishDatabase
import mnxk.kotlintex.mywishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    // Lazy initialization is a design pattern that delays the initialization of an object until the
    // first time it is needed.
    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context) {
        database =
            Room.databaseBuilder(
                context,
                WishDatabase::class.java,
                "wishlist.db",
            ).build()
    }
}
