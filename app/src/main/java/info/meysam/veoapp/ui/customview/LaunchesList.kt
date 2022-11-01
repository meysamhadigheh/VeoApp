package info.meysam.veoapp.ui.customview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.ui.theme.Shapes

@Composable
fun LaunchesList(listLaunches: List<Launch>,onLaunchClick: (Launch) -> Unit) {

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(
            count = listLaunches.size,
            itemContent = {
                LaunchListItem(launch = listLaunches[it], onLaunchClick)
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LaunchListItem(launch: Launch,onLaunchClick: (Launch) -> Unit) {
    val imageLink:String?

//    with(launch.links){
//        imageLink = if (flickr.original.isNotEmpty()){
//            flickr.original[0]
//        }else{
//            patch.large
//        }
//    }

    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 12.dp)
            .fillMaxWidth()
            .clickable(onClick = { onLaunchClick(launch) }),
        shape = Shapes.small,
        elevation = 5.dp
    ) {
//        GlideImage(
//            model = imageLink,
//            contentDescription = launch.name,
//            modifier = Modifier.fillMaxSize().aspectRatio(0.5F),
//            contentScale = if (launch.links.flickr.original.isNotEmpty()) ContentScale.Crop else ContentScale.Fit
//        )
    }
}

