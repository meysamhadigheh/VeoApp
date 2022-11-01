package info.meysam.veoapp.ui.fragments.launchdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.gson.Gson
import info.meysam.veoapp.R
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.ui.customview.LaunchesList
import info.meysam.veoapp.ui.fragments.launches.LaunchesLayout
import info.meysam.veoapp.ui.theme.VeoAppTheme

class LaunchDetailsFragment : Fragment() {

    companion object {
    }

    private lateinit var viewModel: LaunchDetailsViewModel
    private val args : LaunchDetailsFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LaunchDetailsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return ComposeView(requireContext()).apply {
            setContent {
                VeoAppTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        LaunchDetailsLayout(Gson().fromJson(
                            args.launchData,
                            Launch::class.java
                        ))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LaunchDetailsLayout(launch: Launch? = null) {

    launch?.let {
        Card(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 5.dp
        ) {
            Column(
                modifier = Modifier.padding(all = 15.dp)
            ) {
                Text(it.name +  " | " +it.date_utc)
//                Text(it.rocket)
//                GlideImage(
//                    model = it.links.patch.small,
//                    contentDescription = null,
//                    modifier = Modifier.size(72.dp),
//                )
            }
        }
    }
}