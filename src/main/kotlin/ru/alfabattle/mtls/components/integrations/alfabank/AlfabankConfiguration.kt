package ru.alfabattle.mtls.components.integrations.alfabank

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AlfabankProperties::class)
class AlfaConfiguration