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
        ViewState.Loading
    )
    val viewState: LiveData<ViewState> = _viewState

    var job: Job? = null

    fun getAllLaunches() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = launchRepository.getAllLaunches()
            withContext(Dispatchers.Main) {
                when (response) {
                    is NetworkResponse.Success -> _viewState.postValue(ViewState.Ready(response.body))
                    is NetworkResponse.Error -> _viewState.postValue(ViewState.Error(response.error?.message))
                    else -> ViewState.Loading
                }
            }
        }
    }

    sealed class ViewState{
        object Loading: ViewState()
        data class Error(val message: String?):ViewState()
        data class Ready(
            val launches: List<Launch> = ArrayList(),
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