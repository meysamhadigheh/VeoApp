package info.meysam.veoapp.ui.fragments.launches

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Observer
import info.meysam.veoapp.R
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.data.remote.MyApiStatus
import info.meysam.veoapp.ui.customview.LaunchesList
import info.meysam.veoapp.ui.theme.VeoAppTheme

class LaunchesFragment : Fragment() {

    private var getListLaunches: MutableList<Launch> by mutableStateOf(mutableListOf())
    private var isLoading: Boolean by mutableStateOf(false)

    companion object {
        fun newInstance() = LaunchesFragment()
    }

    private lateinit var viewModel: LaunchesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LaunchesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                VeoAppTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        LaunchesLayout(getListLaunches, isLoading = isLoading)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLaunchesData().observe(viewLifecycleOwner) {
            it?.let { myApiResponse ->
                when (myApiResponse.myApiStatus) {
                    MyApiStatus.SUCCESS -> {
                        myApiResponse.data?.let {
                            getListLaunches.clear()
                            getListLaunches.addAll(it)
                            isLoading = false
                        }
                    }
                    MyApiStatus.ERROR -> {
                        isLoading = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    MyApiStatus.LOADING -> {
                        isLoading = true
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchesLayout(getLaunchesList : MutableList<Launch>, isLoading: Boolean) {
    if(isLoading){
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else {
        LaunchesList(listLaunches = getLaunchesList)
    }
}