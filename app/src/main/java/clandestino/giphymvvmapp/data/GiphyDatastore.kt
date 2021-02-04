package clandestino.giphymvvmapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RandomGifEntity::class), version = 1)
abstract class GiphyDatastore : RoomDatabase() {

    abstract fun randomGifDao(): RandomGifDao

    companion object {
        const val dbName = "giphy-app-db"
        const val isDbInMemory = true

        private lateinit var datastore: GiphyDatastore

        fun getInstance(context: Context): GiphyDatastore {
            if (::datastore.isInitialized == false) {
                if (isDbInMemory) {
                    datastore = Room.inMemoryDatabaseBuilder(context, GiphyDatastore::class.java).build()
                } else {
                    datastore = Room.databaseBuilder(context, GiphyDatastore::class.java, dbName).build()
                }
            }
            return datastore
        }
    }
}