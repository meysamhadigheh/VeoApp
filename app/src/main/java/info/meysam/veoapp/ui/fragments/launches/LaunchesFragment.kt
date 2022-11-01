package info.meysam.veoapp.ui.fragments.launches

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import info.meysam.veoapp.R
import info.meysam.veoapp.VeoApplication
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.ui.customview.LaunchesList
import info.meysam.veoapp.ui.theme.VeoAppTheme

class LaunchesFragment : Fragment() {

    companion object {
        fun newInstance() = LaunchesFragment()
    }

    private lateinit var viewModel: LaunchesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(
                this,
                LaunchesViewModelFactory((requireActivity().application as VeoApplication).repository)
            ).get(LaunchesViewModel::class.java)
    }

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                VeoAppTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        MainContent()
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @ExperimentalMaterialApi
    @Composable
    private fun MainContent() {

        val viewState by viewModel.viewState.observeAsState()

        when (val viewState = viewState) {

            is LaunchesViewModel.ViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is LaunchesViewModel.ViewState.Ready -> {
                LaunchesLayout(viewState = viewState,
                    onLaunchClick = {launch ->  viewModel.onLaunchItemCLicked(this, launch) })
            }
            null -> {}

        }

    }
    /**
     * Displays a Network error alert dialog to the user
     *
     */
    private fun displayNetworkError() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.oups)
            .setMessage(R.string.an_error_occurred)
            .setPositiveButton("retry") { dialogInterface, i ->
                viewModel.getAllLaunches()
            }
            .setNegativeButton(android.R.string.cancel
            ) { dialogInterface, i ->
                activity?.onBackPressed()
            }
            .show()
    }
}

@Composable
fun LaunchesLayout(viewState: LaunchesViewModel.ViewState.Ready, onLaunchClick: (Launch) -> Unit) {
    LaunchesList(listLaunches = viewState.launches, onLaunchClick)
}