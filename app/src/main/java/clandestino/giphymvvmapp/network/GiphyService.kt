package clandestino.giphymvvmapp.network

import clandestino.giphymvvmapp.BuildConfig
import clandestino.giphymvvmapp.network.adapters.BooleanTypeAdapter
import clandestino.giphymvvmapp.network.adapters.DateTypeAdapter
import clandestino.giphymvvmapp.network.api.GiphyApi
import com.giphy.sdk.core.models.Image
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class GiphyService: NetworkService() {

    val giphyBaseUrl = "http://api.giphy.com/v1/"
    private var giphyApi: GiphyApi

    protected val gson = GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanTypeAdapter())
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .create()

    init {
        giphyApi = getApiClient(
            giphyBaseUrl,
            BuildConfig.GIPHY_API_KEY,
            gson
        ).create(GiphyApi::class.java)
    }

//    fun getApi(): GiphyApi {
//        if (::giphyApi.isInitialized == false) {
//            giphyApi = getApiClient(
//                giphyBaseUrl,
//                BuildConfig.GIPHY_API_KEY,
//                gson
//            ).create(GiphyApi::class.java)
//        }
//        return giphyApi
//    }

    fun randomGif(): Single<Image> {
        return giphyApi.randomGif()
            .subscribeOn(Schedulers.io())
            .map {
                it.data?.toGif()?.images?.original
            }
    }
}