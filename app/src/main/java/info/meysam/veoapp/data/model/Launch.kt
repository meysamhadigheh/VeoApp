package info.meysam.veoapp.data.model

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
)