plugins {
	`kotlin-dsl`
}

dependencies {
	val kotlinVersion = "1.9.25"
	implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:${kotlinVersion}")
	implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:${kotlinVersion}")
	implementation("org.jetbrains.kotlin.plugin.jpa:org.jetbrains.kotlin.plugin.jpa.gradle.plugin:${kotlinVersion}")
	implementation("org.jetbrains.kotlin.plugin.allopen:org.jetbrains.kotlin.plugin.allopen.gradle.plugin:${kotlinVersion}")

	implementation("io.spring.gradle:dependency-management-plugin:1.1.7")
	implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:2.4.2")
	implementation("org.springframework.boot:spring-boot-gradle-plugin:3.4.3")
}

gradlePlugin {
	plugins {
		create("LicensePlugin") {
			id = "buildlogic.license"
			implementationClass = "buildlogic.gradle.plugin.LicensePlugin"
		}
	}
}
