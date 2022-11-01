package info.meysam.veoapp.data.remote


abstract class LaunchQueryBuilder {

//    fun build(): Map<String, String> {
//        val queryParams = hashMapOf("api_key" to BuildConfig.API_KEY)
//        putQueryParams(queryParams)
//        return queryParams
//    }

    fun build(){

    }

    abstract fun putQueryParams(queryParams: MutableMap<String, String>)

    companion object {

    }
}
