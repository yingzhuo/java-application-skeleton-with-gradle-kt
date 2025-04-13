plugins {
	id("buildlogic.java-conventions")
	id("buildlogic.spring-boot-conventions")
	id("buildlogic.docker-conventions")
}

ext {
	set("dockerTag", "yingzhuo/java-application-skeleton-with-gradle-kotlin:latest")
}

dependencies {

	// 其他子项目
	api(project(":projects-library:utility"))
	api(project(":projects-library:springboot-starter"))

	// spring-boot & spring-core
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// groovy
	// implementation("org.apache.groovy:groovy")

	// jackson
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// slf4j
	implementation("org.slf4j:slf4j-api")

}
