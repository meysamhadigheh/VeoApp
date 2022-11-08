package info.meysam.veoapp.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*


@Entity(tableName = "launch_detail_table")
data class Launch(
    var auto_update: Boolean?,
    @Ignore
    val capsules: List<String>,
    @Ignore
    val cores: List<Core>,
    var date_local: String?,
    var date_precision: String?,
    var date_unix: Int?,
    var date_utc: String?,
    var details: String?,
    @Ignore
    val failures: List<Fail>,
    @Ignore
    val fairings: Fairing?,
    var flight_number: Int?,
    @PrimaryKey
    var id: String,
    var launchpad: String?,
    @Ignore
    val links: Links?,
    var name: String?,
    var net: Boolean?,
    @Ignore
    val payloads: List<String>,
    var rocket: String?,
    var static_fire_date_unix: Int?,
    var static_fire_date_utc: String?,
    var success: Boolean?,
    var tdb: Boolean?,
    var upcoming: Boolean?,
    var window: Int?
) {
    constructor(
        auto_update: Boolean?,
        date_local: String?,
        date_precision: String?,
        date_unix: Int?,
        date_utc: String?,
        details: String?,
        flight_number: Int?,
        id: String,
        launchpad: String?,
        name: String?,
        net: Boolean?,
        rocket: String?,
        static_fire_date_unix: Int?,
        static_fire_date_utc: String?,
        success: Boolean?,
        tdb: Boolean?,
        upcoming: Boolean?,
        window: Int?
    ) : this(
        auto_update,
        emptyList(),
        emptyList(),
        date_local,
        date_precision,
        date_unix,
        date_utc,
        details,
        emptyList(),
        null,
        flight_number,
        id,
        launchpad,
        null,
        name,
        net,
        emptyList(),
        rocket,
        static_fire_date_unix,
        static_fire_date_utc,
        success,
        tdb,
        upcoming,
        window
    )

    /**
     * Change date format to show in 3 char month format
     *
     */

    fun getFireDate(): String? {
        if (static_fire_date_utc == null) return null
        val firstDate = static_fire_date_utc
        val englishIsrael = Locale.forLanguageTag("en-US")
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", englishIsrael)
        val date = formatter.parse(firstDate)
        val desiredFormat = SimpleDateFormat("dd, MMM yyyy - HH:mm", englishIsrael).format(date)
        return desiredFormat
    }
}

val sampleLaunch = Launch(
    true, emptyList(), emptyList(), "", "", 0, "", null, emptyList(),
    Fairing(null, null, null), 0, "", null,
    Links(
        null,
        Flickr(
            original = listOf("https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg"),
            emptyList()
        ),
        Patch(null, null),
        null,
        Reddit(null, null, null, null),
        null,
        null,
        null
    ), "", true, emptyList(), null, null, null, null, true, true, 0
)