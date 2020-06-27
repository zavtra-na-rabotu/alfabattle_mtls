package ru.alfabattle.mtls.components.integrations.alfabank

import org.springframework.stereotype.Service
import ru.alfabattle.mtls.components.integrations.alfabank.atms.Atm
import ru.alfabattle.mtls.components.integrations.alfabank.client.AlfabankClient

@Service
class AlfabankService(
    private val alfabankClient: AlfabankClient,
    private val alfabankProperties: AlfabankProperties
) {
    fun findAtmByDeviceId(deviceId: Int): Atm? {
        val allAtms = alfabankClient.getAtms(alfabankProperties.clientId).data.atms
        return allAtms.find { it.deviceId == deviceId }
    }
}