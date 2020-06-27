import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"

	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "ru.alfabattle"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging:1.7.9")

	// Jackson
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")

	// SSLContextBuilder
	implementation("org.apache.httpcomponents:httpclient:4.5.12")

	// Feign
	implementation("io.github.openfeign:feign-core:10.8")
	implementation("io.github.openfeign:feign-jackson:10.8")

	// Websocket
	implementation("org.springframework:spring-websocket:5.2.7.RELEASE")
	implementation("org.springframework:spring-messaging:5.2.7.RELEASE")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		allWarningsAsErrors = true
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
