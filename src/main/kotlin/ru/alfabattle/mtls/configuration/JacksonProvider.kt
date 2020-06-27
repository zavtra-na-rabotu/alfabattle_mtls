package ru.alfabattle.mtls.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
internal class JacksonProvider {

    @Primary
    @Bean
    fun jsonObjectMapper(): ObjectMapper = ObjectMapper()
        .registerModule(ParameterNamesModule())
        .registerModule(JavaTimeModule())
        .registerModule(KotlinModule())
        .registerModule(Jdk8Module())
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
}