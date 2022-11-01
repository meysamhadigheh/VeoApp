package info.meysam.veoapp.ui.fragments.launches

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.haroldadmin.cnradapter.NetworkResponse
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.repository.impl.LaunchRepository
import kotlinx.coroutines.*

class LaunchesViewModel(private val launchRepository: LaunchRepository) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData(
        ViewState.Ready()
    )
    val viewState: LiveData<ViewState> = _viewState

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    var job: Job? = null

    fun getAllLaunches() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = launchRepository.getAllLaunches()
            withContext(Dispatchers.Main) {
                when (response) {
                    is NetworkResponse.Success -> {

                        viewState.value?.let { viewState ->
                           when(viewState){
                               is ViewState.Ready->{
                                   _viewState.value = viewState.copy(launches = response.body)
                               }
                               else -> ViewState.Loading
                           }
                        }
                        loading.value = false
                    }
                    is NetworkResponse.Error -> {

                        onError("Error : ${response.error?.message} ")
                        loading.value = false
                    }
                    else -> ViewState.Loading
                }
            }
        }
    }
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    sealed class ViewState{
        object Loading: ViewState()
        data class Ready(
            var launches: List<Launch> = ArrayList(),
        ) : ViewState()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun onLaunchItemCLicked(fragment: LaunchesFragment,launch: Launch) {
        val direction = LaunchesFragmentDirections.actionLaunchesFragmentToLaunchDetailFragment(
            Gson().toJson(launch))
        findNavController(fragment).navigate(direction)
    }

}