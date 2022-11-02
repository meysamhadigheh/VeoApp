package info.meysam.veoapp.data.model
import java.text.SimpleDateFormat
import java.util.*

data class Launch(
    val auto_update: Boolean,
    val capsules: List<String>,
    val cores: List<Core>,
    val date_local: String,
    val date_precision: String,
    val date_unix: Int,
    val date_utc: String,
    val details: String?,
    val failures: List<Fail>,
    val fairings: Fairing,
    val flight_number: Int,
    val id: String,
    val launchpad: String?,
    val links: Links,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String?,
    val static_fire_date_unix: Int?,
    val static_fire_date_utc: String?,
    val success: Boolean?,
    val tdb: Boolean,
    val upcoming: Boolean,
    val window: Int?
){
    /**
     * Change date format to show in 3 char month format
     *
     */

    fun getFireDate():String?{
        if (static_fire_date_utc==null) return null
        val firstDate = static_fire_date_utc
        val englishIsrael = Locale.forLanguageTag("en-US")
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",englishIsrael)
        val date = formatter.parse(firstDate)
        val desiredFormat = SimpleDateFormat("dd, MMM yyyy - HH:mm",englishIsrael).format(date)
        return desiredFormat
    }
}

val sampleLaunch = Launch(
    true, emptyList(), emptyList(), "", "", 0, "", null, emptyList(),
    Fairing(null, null, null), 0, "", null,
    Links(
        null, Flickr(original = listOf("https://live.staticflickr.com/65535/49635401403_96f9c322dc_o.jpg"), emptyList()),
        Patch(null, null), null,
        Reddit(null, null, null, null), null, null, null
    ), "", true, emptyList(), null, null, null, null, true, true, 0
)