plugins {
	id("buildlogic.java-conventions")
}

dependencies {

	// 其他子项目
	api(project(":projects-library:utility"))

	// spring-boot & spring
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	compileOnly("org.springframework.boot:spring-boot-autoconfigure")
	compileOnly("org.springframework.boot:spring-boot-starter-aop")
	compileOnly("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.springframework.boot:spring-boot-starter-security")
	compileOnly("org.springframework.boot:spring-boot-starter-logging")
	compileOnly("org.springframework.boot:spring-boot-starter-validation")
	compileOnly("org.springframework.boot:spring-boot-starter-jdbc")
	compileOnly("org.springframework.boot:spring-boot-configuration-processor")
	compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
	compileOnly("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// slf4j
	implementation("org.slf4j:slf4j-api")

}
