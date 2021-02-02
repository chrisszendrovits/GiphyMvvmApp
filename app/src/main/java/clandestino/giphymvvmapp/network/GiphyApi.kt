package clandestino.giphymvvmapp.network

import io.reactivex.Single
import retrofit2.http.GET
import com.giphy.sdk.core.network.response.ListMediaResponse

interface GiphyApi {
    @GET("gifs/trending")
    fun trendingGifs(): Single<ListMediaResponse>
}