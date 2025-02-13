plugins {
	`kotlin-dsl`
}

ext {
	set("springBootVersion", "3.4.2")
}

repositories {
	mavenLocal()
	maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
	maven { name = "阿里云(Spring)"; url = uri("https://maven.aliyun.com/repository/spring/") }
	maven { name = "Spring(GA)"; url = uri("https://repo.spring.io/release") }
	maven { name = "Spring(Milestone)"; url = uri("https://repo.spring.io/milestone") }
	maven { name = "Spring(Snapshot)"; url = uri("https://repo.spring.io/snapshot") }
	mavenCentral()
	google()
}

dependencies {
	implementation("io.spring.gradle:dependency-management-plugin:1.1.7")
	implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:2.4.2")
	implementation("org.springframework.boot:spring-boot-gradle-plugin:3.4.2")
}
