package clandestino.giphymvvmapp.network

import clandestino.giphymvvmapp.BuildConfig
import clandestino.giphymvvmapp.network.adapters.BooleanTypeAdapter
import clandestino.giphymvvmapp.network.adapters.DateTypeAdapter
import clandestino.giphymvvmapp.network.api.GiphyApi
import com.google.gson.GsonBuilder
import java.util.*

class GiphyService: NetworkService() {

    val giphyBaseUrl = "http://api.giphy.com/v1/"
    private lateinit var giphyApi: GiphyApi

    protected val gson = GsonBuilder()
        .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
        .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanTypeAdapter())
        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
        .create()

    fun getApi(): GiphyApi {
        if (::giphyApi.isInitialized == false) {
            giphyApi = getApiClient(
                giphyBaseUrl,
                BuildConfig.GIPHY_API_KEY,
                gson
            ).create(GiphyApi::class.java)
        }
        return giphyApi
    }
}