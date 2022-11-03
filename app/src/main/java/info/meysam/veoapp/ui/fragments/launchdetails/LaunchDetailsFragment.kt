package info.meysam.veoapp.ui.fragments.launchdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import info.meysam.veoapp.R
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.ui.theme.Shapes
import info.meysam.veoapp.ui.theme.Typography
import info.meysam.veoapp.ui.theme.VeoAppTheme

class LaunchDetailsFragment : Fragment() {

    companion object {
    }

    private lateinit var viewModel: LaunchDetailsViewModel
    private val args: LaunchDetailsFragmentArgs by navArgs()


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
                        LaunchDetailsLayout(
                            Gson().fromJson(
                                args.launchData,
                                Launch::class.java
                            )
                        ) { id -> viewModel.openYoutube(requireContext(),id) }
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchDetailsLayout(launch: Launch? = null, youtubeClick: (String) -> Unit) {

    launch?.let {
        Column {

            launch.links.webcast?.let {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_ondemand_video_24),
                    contentDescription = "youtube",
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .background(Color.Red, shape = CircleShape)
                        .clickable(onClick = {
                            youtubeClick(it)
                        })
                        .padding(8.dp)
                )
            }

            MyBasicCard(modifier = Modifier) {
                TitleView(title = stringResource(R.string.info))
                TextWithDetail(title = stringResource(R.string.name), detail = launch.name)
                TextWithDetail(
                    title = stringResource(R.string.fire_date),
                    detail = launch.getFireDate()
                )
                TextWithDetail(
                    title = stringResource(R.string.window),
                    detail = launch.window.toString()
                )
                TextWithDetail(title = stringResource(R.string.rocket), detail = launch.rocket)
                TextWithState(title = stringResource(R.string.success), status = launch.success)
                TextWithState(title = stringResource(R.string.upcoming), status = launch.upcoming)
                TextWithDetail(title = stringResource(R.string.flight_number), detail = launch.name)
            }
            launch.details?.let {
                MyBasicCard(modifier = Modifier) {
                    TitleView(title = stringResource(R.string.details))
                    Text(
                        text = it,
                        style = Typography.caption,
                        color = Color.White,
                        modifier = Modifier
                            .padding(6.dp)
                            .padding(bottom = 10.dp),
                        lineHeight = 17.sp
                    )
                }
            }
            MyBasicCard(modifier = Modifier) {
                TitleView(title = stringResource(R.string.cores_title))
                launch.cores.forEach {
                    Separator()
                    TextWithDetail(title = stringResource(R.string.core), detail = it.core)
                    TextWithDetail(
                        title = stringResource(R.string.flight),
                        detail = it.flight.toString()
                    )
                    TextWithState(title = stringResource(R.string.gridfins), status = it.gridfins)
                    TextWithState(title = stringResource(R.string.legs), status = it.legs)
                    TextWithState(title = stringResource(R.string.reused), status = it.reused)
                    TextWithState(
                        title = stringResource(R.string.landing_attempt),
                        status = it.landing_attempt
                    )
                    TextWithState(
                        title = stringResource(R.string.landing_success),
                        status = it.landing_success
                    )
                    TextWithDetail(
                        title = stringResource(R.string.landing_type),
                        detail = it.landing_type
                    )
                    TextWithDetail(title = stringResource(R.string.landpad), detail = it.landpad)
                }
            }
        }
    }
}

@Composable
fun Separator() {
    Box(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color.LightGray)
    )
}

@Composable
fun MyBasicCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(modifier = modifier.padding(8.dp), shape = Shapes.medium, backgroundColor = Color.Black) {
        Column(content = content)
    }
}

@Composable
fun TitleView(title: String?) {
    if (title == null) return

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title, style = Typography.h6, color = Color.White, modifier = Modifier
                .align(
                    Alignment.CenterHorizontally
                )
                .padding(10.dp)
        )
        Box(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.LightGray)
        )
    }
}

@Composable
fun TextWithState(title: String?, status: Boolean?) {
    if (title == null || status == null) return

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = Typography.subtitle1,
            color = Color.White,
            modifier = Modifier.align(
                Alignment.CenterStart
            )
        )
        val painter =
            if (status) painterResource(id = R.drawable.ic_baseline_check_circle_24) else painterResource(
                id = R.drawable.ic_baseline_cancel_24
            )
        Image(
            painter = painter, contentDescription = "status", modifier = Modifier.align(
                Alignment.CenterEnd
            )
        )
    }
}

@Composable
fun TextWithDetail(title: String?, detail: String?) {
    if (title == null || detail == null) return
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = Typography.subtitle1,
            color = Color.White,
            modifier = Modifier.align(
                Alignment.CenterStart
            )
        )
        Text(
            text = detail,
            style = Typography.caption,
            color = Color.White,
            modifier = Modifier
                .align(
                    Alignment.CenterEnd
                )
                .padding(end = 4.dp)
        )
    }
}