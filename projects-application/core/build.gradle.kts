plugins {
	id("buildlogic.java-conventions")
	id("buildlogic.spring-boot-conventions")
}

ext {
	set("bootMainClass", "com.mycompany.myproject.core.ApplicationBoot")
}

dependencies {

	// 其他子项目
	implementation(project(":projects-library:utility"))
	implementation(project(":projects-library:springboot-starter"))

	// spring-boot & spring-core
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// slf4j
	implementation("org.slf4j:slf4j-api")
}
