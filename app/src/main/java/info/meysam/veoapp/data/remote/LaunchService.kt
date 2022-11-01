package info.meysam.veoapp.data.remote;

import info.meysam.veoapp.BuildConfig
import info.meysam.veoapp.data.model.Launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import info.meysam.veoapp.data.model.ErrorResponse

import retrofit2.http.GET;
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

interface LaunchService {

    @GET("launches")
    suspend fun getLaunches():NetworkResponse<ArrayList<Launch>, ErrorResponse>

    companion object{

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
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .client(httpClient)
                .build()
        }

        val instance : LaunchService by lazy{
            retrofit.create(LaunchService::class.java)
        }
    }

}