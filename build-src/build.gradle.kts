plugins {
	`kotlin-dsl`
}

dependencies {
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
