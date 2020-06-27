package ru.alfabattle.mtls.components.integrations.alfabank.atms

import java.util.Date

data class AtmsResponse(
    val data: AtmsData,
    val success: Boolean,
    val total: Int
)

data class AtmsData(
    val bankLicense: String,
    val atms: List<Atm>
)

data class Atm(
    val deviceId: Int,
    val recordUpdated: Date,
    val availablePaymentSystems: List<String?>,
    val cashInCurrencies: List<String?>,
    val cashOutCurrencies: List<String?>,
    val publicAccess: String,
    val services: AtmService,
    val timeShift: Int,
    val timeAccess: AtmTimeAccess,
    val coordinates: AtmCoordinates,
    val address: AtmAddress,
    val addressComments: String?,
    val supportInfo: AtmSupportInfo,
    val nfc: String
)

data class AtmSupportInfo(
    val phone: String,
    val email: String,
    val other: String
)

data class AtmAddress(
    val mode: String,
    val city: String,
    val location: String
)

data class AtmCoordinates(
    val longitude: String?,
    val latitude: String?
)

data class AtmTimeAccess(
    val mode: String,
    val schedule: String
)

data class AtmService(
    val cardCashOut: String,
    val cardCashIn: String,
    val cashOut: String,
    val cashIn: String,
    val cardPayments: String,
    val payments: String
)