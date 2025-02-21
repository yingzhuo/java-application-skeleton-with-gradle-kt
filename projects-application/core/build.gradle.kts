plugins {
	id("buildlogic.java-conventions")
	id("buildlogic.spring-boot-conventions")
	id("buildlogic.dist-conventions")
}

ext {
	set("bootMainClass", "com.mycompany.myproject.core.ApplicationBoot")
}

dependencies {

	// 其他子项目
	api(project(":projects-library:utility"))
	api(project(":projects-library:springboot-starter"))

	// spring-boot & spring-core
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// groovy
	implementation("org.apache.groovy:groovy")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// slf4j
	implementation("org.slf4j:slf4j-api")

}
