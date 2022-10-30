package info.meysam.veoapp.ui.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val _movieId = MutableLiveData<Long>()
    val movieId: LiveData<Long> = _movieId

    fun setMovieId(id: Long) {
        _movieId.value = id
    }



}
