package info.meysam.veoapp.ui.customview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.ui.theme.Shapes
import info.meysam.veoapp.R
import info.meysam.veoapp.ui.theme.Typography

@Composable
fun LaunchesList(listLaunches: List<Launch>, onLaunchClick: (Launch) -> Unit) {

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(
            count = listLaunches.size,
            itemContent = {
                LaunchListItem(launch = listLaunches[it], onLaunchClick)
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalUnitApi::class)
@Composable
fun LaunchListItem(launch: Launch, onLaunchClick: (Launch) -> Unit, imageDefault: Painter? = null) {
    val imageLink: String?
    var lines by remember { mutableStateOf(0) }


    with(launch.links) {
        imageLink = if (this?.flickr?.original?.isNotEmpty() == true) {
            this.flickr.original[0]
        } else {
            this?.patch?.large
        }
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onLaunchClick(launch) }),
        shape = Shapes.medium,
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.65F)
        ) {
            GlideImage(
                model = imageDefault ?: imageLink,
                contentDescription = launch.name,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = if (launch.links?.flickr?.original?.isNotEmpty() == true) ContentScale.Crop else ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0x00000000),
                                Color(0xFF000000),
                            )
                        )
                    ),
            ) {
                Column(Modifier.padding(start = 6.dp, bottom = 12.dp, top = 20.dp, end = 6.dp)) {
                    launch.name?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            style = Typography.body1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    launch.details?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            style = Typography.caption,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 6.dp),
                            lineHeight = 17.sp,
                            onTextLayout = { res -> lines = res.lineCount }
                        )
                        for (i in lines..1) {
                            Text(" ", style = Typography.caption)
                        }
                    }?:apply {
                        Text(
                            text = " \n ",
                            color = Color.White,
                            style = Typography.caption,
                            modifier = Modifier.padding(start = 6.dp),
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
private fun LaunchListItemPreview() {

    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 12.dp)
            .fillMaxWidth()
            .clickable(onClick = { }),
        shape = Shapes.small,
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.75F)
        ) {
            GlideImage(
                model = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(0.75F),
                contentScale = ContentScale.Fit
            )
        }
    }
}

