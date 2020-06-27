package ru.alfabattle.mtls.components.integrations.alfabank

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("alfabank")
class AlfabankProperties {
    lateinit var url: String
    lateinit var clientId: String
    lateinit var clientSecret: String

    val ssl = SSL()

    class SSL {
        lateinit var certificate: String
        lateinit var p12path: String
        lateinit var password: String
    }
}