package clandestino.giphymvvmapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface RandomGifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(randomGif: RandomGifEntity): Single<Long>

    @Query("SELECT * FROM randomGif WHERE id = :id")
    fun select(id: Long): Maybe<RandomGifEntity>

    @Query("DELETE FROM randomGif")
    fun deleteAll(): Completable
}