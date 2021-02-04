package clandestino.giphymvvmapp.data

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GiphyRepository(context: Context) {

    private val randomGifDao: RandomGifDao

    init {
        randomGifDao = GiphyDatastore.getInstance(context).randomGifDao()
    }

    fun insertRandomGif(url: String): Single<Long> {
        val entity = RandomGifEntity(url)
        return randomGifDao.insert(entity).subscribeOn(Schedulers.io())
    }

    fun selectRandomGif(): Maybe<RandomGifEntity> {
        return randomGifDao.select(1).subscribeOn(Schedulers.io())
    }

    fun deleteRandomGifs(): Completable = randomGifDao.deleteAll().subscribeOn(Schedulers.io())
}