package info.meysam.veoapp.data.model

import java.util.UUID

data class Core(
    val core: UUID?,
    val flight: Int?,
    val gridfins: Boolean?,
    val landing_attempt: Boolean?,
    val landing_success: Boolean?,
    val landing_type: String?,
    val landpad: UUID?,
    val legs: Boolean?,
    val reused: Boolean?
)