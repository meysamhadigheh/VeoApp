package info.meysam.veoapp.data.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "launch_detail_table")
data class Launch(
    val auto_update: Boolean ?=false,
//        @Embedded
//    @TypeConverters(StringsListConverters::class)
//    val capsules: List<String?> = listOf(),
//    @Embedded
//    @TypeConverters(CoreConverters::class)
//    val cores: List<Core> = ArrayList(),
    val date_local: String?="",
    val date_precision: String?="",
    val date_unix: Int=0,
    val date_utc: String?="",
    val details: String?="",
//    @Embedded
//    @TypeConverters(FailsConverters::class)
//    val failures: List<Fail> = ArrayList(),
    @Embedded
    @TypeConverters(FairingsConverters::class)
    val fairings: Fairing?=null,
    val flight_number: Int?=0,
    @NonNull
    @PrimaryKey
    val id: String="",
    val launchpad: String?="",
//    @Embedded
//    @TypeConverters(LinksConverters::class)
//    val links: Links?=null,
    val name: String?=null,
    val net: Boolean?=null,
//    @Embedded
//    @TypeConverters(StringsListConverters::class)
//    val payloads: List<String> = ArrayList(),
    val rocket: String?=null,
    val static_fire_date_unix: Int?=null,
    val static_fire_date_utc: String?=null,
    val success: Boolean?=null,
    val tdb: Boolean?=null,
    val upcoming: Boolean?=null,
    val window: Int?=null
)