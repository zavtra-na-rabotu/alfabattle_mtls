package ru.alfabattle.mtls.components.integrations.alfabank

import org.springframework.stereotype.Service
import ru.alfabattle.mtls.components.integrations.alfabank.atms.Atm
import ru.alfabattle.mtls.components.integrations.alfabank.client.AlfabankClient
import ru.alfabattle.mtls.extensions.round

@Service
class AlfabankService(
    private val alfabankClient: AlfabankClient,
    private val alfabankProperties: AlfabankProperties
) {
    fun findAtmByDeviceId(deviceId: Int): Atm? {
        val allAtms = alfabankClient.getAtms(alfabankProperties.clientId).data.atms
        return allAtms.find { it.deviceId == deviceId }
    }

    fun findNearest(latitude: String, longitude: String, payments: Boolean): Atm? {
        val allAtms = alfabankClient.getAtms(alfabankProperties.clientId).data.atms

        return allAtms.find {
            it.coordinates.latitude?.round() == latitude.round() &&
            it.coordinates.longitude?.round() == longitude.round() &&
            (it.services.payments == "Y") == payments
        }
    }
}