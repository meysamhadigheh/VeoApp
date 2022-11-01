package info.meysam.veoapp.data.model

import androidx.room.Embedded
import androidx.room.TypeConverters

data class Flickr(
    @Embedded
    @TypeConverters(StringsListConverters::class)
    val original: List<String> = ArrayList(),
    @Embedded
    @TypeConverters(StringsListConverters::class)
    val small: List<String>  = ArrayList()
)