package ru.alfabattle.mtls.components.integrations.alfabank.client

import feign.Client
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.alfabattle.mtls.components.integrations.alfabank.AlfabankProperties
import ru.alfabattle.mtls.configuration.FeignBuilderFactory
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLSocketFactory

@Configuration
class AlfabankClientProvider {
    @Bean
    fun alfabankClient(
        feignBuilderFactory: FeignBuilderFactory,
        alfabankProperties: AlfabankProperties
    ): AlfabankClient {
        return feignBuilderFactory()
            .client(
                Client.Default(
                    getSSLSocketFactory(alfabankProperties.ssl.p12path, alfabankProperties.ssl.password),
                    HttpsURLConnection.getDefaultHostnameVerifier()
                ))
            .target(AlfabankClient::class.java, alfabankProperties.url)
    }

    private fun getSSLSocketFactory(keyPath: String, password: String): SSLSocketFactory {
        val p12Url = this.javaClass.classLoader.getResource(keyPath) ?:
            error("p12 key file not found")

        return SSLContextBuilder
            .create()
            .loadKeyMaterial(p12Url, password.toCharArray(), password.toCharArray())
            .build()
            .socketFactory
    }
}