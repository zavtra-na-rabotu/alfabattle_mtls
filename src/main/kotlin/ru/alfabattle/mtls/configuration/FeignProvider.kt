package ru.alfabattle.mtls.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Feign
import feign.codec.Decoder
import feign.codec.Encoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.Timestamp
import java.time.LocalDateTime

typealias FeignBuilderFactory = () -> Feign.Builder

@Configuration
class FeignProvider {
    @Bean
    fun feignBuilderFactory(
        @Qualifier("jsonObjectMapper") jsonObjectMapper: ObjectMapper
    ): FeignBuilderFactory {
        val jacksonDecoder = JacksonDecoder(jsonObjectMapper)
        val defaultDecoder = Decoder.Default()

        val jacksonEncoder = JacksonEncoder(jsonObjectMapper)
        val defaultEncoder = Encoder.Default()

        return {
            Feign.builder()
                .encoder { data, type, template ->
                    when (type) {
                        String::class.java -> defaultEncoder.encode(data, type, template)
                        else -> jacksonEncoder.encode(data, type, template)
                    }
                }
                .decoder { response, type ->
                    when {
                        type === ByteArray::class.java -> defaultDecoder.decode(response, type)
                        type === String::class.java -> defaultDecoder.decode(response, type)
                        else -> jacksonDecoder.decode(response, type)
                    }
                }
        }
    }
}