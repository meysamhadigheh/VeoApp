package info.meysam.veoapp.data.remote

data class MyApiResponse<out T>(val myApiStatus: MyApiStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): MyApiResponse<T> =
            MyApiResponse(myApiStatus = MyApiStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): MyApiResponse<T> =
            MyApiResponse(myApiStatus = MyApiStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): MyApiResponse<T> =
            MyApiResponse(myApiStatus = MyApiStatus.LOADING, data = data, message = null)
    }
}

enum class MyApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}