import java.util.*

plugins {
	`kotlin-dsl`
}

file("${rootDir}/../gradle.properties")
	.reader(Charsets.ISO_8859_1)
	.use {
		val properties = Properties()
		properties.load(it)
		properties.forEach { key, value ->
			ext.set(key.toString(), value.toString())
		}
	}

dependencies {
	implementation("org.springframework.boot:spring-boot-gradle-plugin:${project.extra["springBootVersion"]}")
	implementation("io.spring.gradle:dependency-management-plugin:${project.extra["dependencyManagementPluginVersion"]}")
	implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:${project.extra["gitPluginVersion"]}")
}
