package info.meysam.veoapp.data.model

import java.util.*

data class Launch(
    val auto_update: Boolean,
    val capsules: List<UUID>,
    val cores: List<Core>,
    val crew: List<Crew?>,
    val date_local: String,
    val date_precision: String,
    val date_unix: Int,
    val date_utc: String,
    val details: String?,
    val failures: List<FailureLaunch>,
    val fairings: Fairings,
    val flight_number: Int,
    val id: String,
    val launch_library_id: String,
    val launchpad: String,
    val links: Links,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String,
    val ships: List<UUID>,
    val static_fire_date_unix: Int?,
    val static_fire_date_utc: String?,
    val success: Boolean?,
    val tbd: Boolean,
    val upcoming: Boolean,
    val window: Int?
)