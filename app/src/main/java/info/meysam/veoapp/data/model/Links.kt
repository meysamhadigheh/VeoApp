package info.meysam.veoapp.data.model

import androidx.room.Embedded
import androidx.room.TypeConverters

data class Links(
    val article: String?=null,
    @Embedded
    @TypeConverters(FlickerConverters::class)
    val flickr: Flickr?=null,
    //val patch: Patch,
    val presskit: String,
    //val reddit: Reddit,
    val webcast: String?=null,
    val wikipedia: String?=null,
    val youtube_id: String?=null
)