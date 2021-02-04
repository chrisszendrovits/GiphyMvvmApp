package clandestino.giphymvvmapp.data

import android.content.Context
import clandestino.giphymvvmapp.network.GiphyService
import io.reactivex.Observable

class GiphyDataManager(context: Context) {

    private val api: GiphyService
    private val repository: GiphyRepository

    init {
        api = GiphyService()
        repository = GiphyRepository(context)
    }

    fun randomGif(): Observable<RandomGifEntity> {
        return Observable.concat(
            repository.selectRandomGif().toObservable(),
            api.randomGif()
                .map { it.gifUrl?.let { url -> RandomGifEntity(url) } }
                .doOnSuccess { it?.let { repository.insertRandomGif(it.url) } }
                .toObservable()
        )
    }
}