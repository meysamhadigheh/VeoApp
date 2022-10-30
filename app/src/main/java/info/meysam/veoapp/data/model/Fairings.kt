package info.meysam.veoapp.data.model

import java.util.UUID

data class Fairings(
    val recovered: Boolean?,
    val recovery_attempt: Boolean?,
    val reused: Boolean?,
    val ships: List<UUID>
)