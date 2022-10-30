package info.meysam.veoapp.ui.customview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import info.meysam.veoapp.data.model.Launch

@Composable
fun LaunchesList(listLaunches: MutableList<Launch>,onLaunchClick: (Launch) -> Unit) {

    LazyColumn() {
        items(
            count = listLaunches.size,
            itemContent = {
                LaunchListItem(launch = listLaunches[it],onLaunchClick)
            }
        )
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LaunchListItem(launch: Launch,onLaunchClick: (Launch) -> Unit) {

    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 12.dp)
            .fillMaxWidth()
            .clickable(onClick = { onLaunchClick(launch) }),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(all = 15.dp)
        ) {
            Text(launch.name +  " | " +launch.date_utc)
            Text(launch.rocket)
            GlideImage(
                model = launch.links.patch.small,
                contentDescription = null,
                modifier = Modifier.size(72.dp),
            )
        }
    }
}

