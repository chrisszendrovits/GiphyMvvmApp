package clandestino.giphymvvmapp.network

import clandestino.giphymvvmapp.BuildConfig
import clandestino.giphymvvmapp.network.adapters.BooleanTypeAdapter
import clandestino.giphymvvmapp.network.adapters.DateTypeAdapter
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

class NetworkService {

    companion object {
        val giphyBaseUrl = "http://api.giphy.com/v1/"

        private val gson = GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanTypeAdapter())
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .create()

        private fun getClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain -> return@addInterceptor addApiKeyToChain(chain) }
                .build()
        }

        private fun addApiKeyToChain(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url()
            val newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.GIPHY_API_KEY).build()
            request.url(newUrl)
            return chain.proceed(request.build())
        }

        private fun getHttpClient(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
        }

        private lateinit var giphyApi: GiphyApi

        fun getGiphyApi(): GiphyApi {
            if (::giphyApi.isInitialized == false) {
                giphyApi = getHttpClient(giphyBaseUrl).create(GiphyApi::class.java)
            }
            return giphyApi
        }
    }
}