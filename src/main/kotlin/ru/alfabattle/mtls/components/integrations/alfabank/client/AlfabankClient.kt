package ru.alfabattle.mtls.components.integrations.alfabank.client

import feign.Headers
import feign.Param
import feign.RequestLine
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import ru.alfabattle.mtls.components.integrations.alfabank.atms.AtmsResponse

/**
 * [Alfa API](https://apiws.alfabank.ru/alfabank/alfadevportal) client
 */
interface AlfabankClient {
    /**
     * [Get info about atms](https://apiws.alfabank.ru/alfabank/alfadevportal/atm-service/atms)
     */
    @RequestLine("GET /atm-service/atms")
    @Headers(
        "Accept: $APPLICATION_JSON_VALUE",
        "x-ibm-client-id: {clientId}"
    )
    fun getAtms(
        @Param("clientId") clientId: String
    ): AtmsResponse

    /**
     * [Get info about available atms functions](https://apiws.alfabank.ru/alfabank/alfadevportal/atm-service/atms/status)
     */
    @RequestLine("GET /atm-service/atms/status")
    @Headers(
        "Accept: $APPLICATION_JSON_VALUE",
        "x-ibm-client-id: {clientId}"
    )
    fun getAtmsStatus(
        @Param("clientId") clientId: String
    ): String
}