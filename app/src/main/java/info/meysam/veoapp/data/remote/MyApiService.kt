package info.meysam.veoapp.data.remote;


import info.meysam.veoapp.data.model.Launch
import java.util.ArrayList;

import retrofit2.http.GET;

interface MyApiService {


    @GET("launches")
    suspend fun getLaunches():ArrayList<Launch>

}