package info.meysam.veoapp.ui.fragments.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import info.meysam.veoapp.data.remote.MyApiClient
import info.meysam.veoapp.data.remote.MyApiResponse
import kotlinx.coroutines.Dispatchers

class LaunchesViewModel : ViewModel() {

    fun getLaunchesData() = liveData(Dispatchers.IO) {
        emit(MyApiResponse.loading(data = null))
        try {
            emit(MyApiResponse.success(data = MyApiClient.MY_API_SERVICE.getLaunches()))
        } catch (exception: Exception) {
            emit(
                MyApiResponse.error(
                    data = null,
                    message = exception.message ?:  "Error Occurred!"
                )
            )
        }
    }
}