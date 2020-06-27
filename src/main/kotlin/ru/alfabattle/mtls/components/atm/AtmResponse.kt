package ru.alfabattle.mtls.components.atm

data class AtmResponse(
    val deviceId: Int,
    val latitude: String?,
    val longitude: String?,
    val city: String,
    val location: String,
    val payments: Boolean
)

data class ErrorResponse(
    val status: String
)