package info.meysam.veoapp.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MyApiClient {
    private const val BASE_URL: String = "https://api.spacexdata.com/v5/"

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val interceptor : HttpLoggingInterceptor by lazy{
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    val MY_API_SERVICE : MyApiService by lazy{
        retrofit.create(MyApiService::class.java)
    }
}