package ru.alfabattle.mtls.components.integrations.alfabank

import org.springframework.stereotype.Service
import ru.alfabattle.mtls.components.integrations.alfabank.atms.Atm
import ru.alfabattle.mtls.components.integrations.alfabank.client.AlfabankClient
import ru.alfabattle.mtls.extensions.degToRad
import ru.alfabattle.mtls.extensions.radToDeg
import java.lang.Math.PI
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

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

        return allAtms
            .filter { it.coordinates.latitude != null || it.coordinates.longitude != null }
            .filter { (it.services.payments == "Y") == payments }
            .minBy { distance(it.coordinates.latitude!!.toDouble(), it.coordinates.longitude!!.toDouble(), latitude.toDouble(), longitude.toDouble())
        }
    }

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = sin(lat1.degToRad()) * sin(lat2.degToRad()) + cos(lat1.degToRad()) * cos(lat2.degToRad()) * cos(theta.degToRad())
        dist = acos(dist).radToDeg()
        dist *= 60 * 1.1515
        return dist
    }
}