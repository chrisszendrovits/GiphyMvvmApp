package clandestino.giphymvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clandestino.giphymvvmapp.network.NetworkService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val disposibles = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val single = NetworkService.getGiphyApi().trendingGifs()
            .subscribeOn(Schedulers.io())
            .subscribe({
                val test = it
            }, {
                val error = it
            })

        disposibles.add(single)
    }

    override fun onDestroy() {
        disposibles.dispose()
        super.onDestroy()
    }
}