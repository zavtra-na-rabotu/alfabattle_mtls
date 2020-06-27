package ru.alfabattle.mtls.components.atm

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.alfabattle.mtls.components.integrations.alfabank.AlfabankService

@RestController
@RequestMapping("/atms")
class AtmController(
    private val alfabankService: AlfabankService
) {
    @GetMapping("/{deviceId}")
    fun getAtm(@PathVariable deviceId: Int): ResponseEntity<*> {
        val atm = alfabankService.findAtmByDeviceId(deviceId)
            ?: return ResponseEntity(ErrorResponse(status = "atm not found"), NOT_FOUND)

        val atmResponse = AtmResponse(
            deviceId = atm.deviceId,
            latitude = atm.coordinates.latitude,
            longitude= atm.coordinates.longitude,
            location = atm.address.location,
            city = atm.address.city,
            payments = atm.services.payments == "Y"
        )

        return ResponseEntity(atmResponse, OK)
    }
}