package info.meysam.veoapp.ui.fragments.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.meysam.veoapp.repository.impl.LaunchRepository


class LaunchesViewModelFactory(
    private val launchRepository: LaunchRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaunchesViewModel::class.java)) {
            return LaunchesViewModel(launchRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
