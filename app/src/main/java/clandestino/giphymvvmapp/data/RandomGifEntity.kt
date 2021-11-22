package clandestino.giphymvvmapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "randomGif")
data class RandomGifEntity(@ColumnInfo(name = "url") val url: String) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}