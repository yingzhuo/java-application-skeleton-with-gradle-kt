/* =====================================================================================================================
 * Java项目构建逻辑
 * =================================================================================================================== */

plugins {
	id("java")
	id("java-library")
	id("io.spring.dependency-management")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.2")
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17

	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}

	// withSourcesJar()
	// withJavadocJar()
}

tasks.named<Jar>("jar") {
	archiveFileName = "${project.name}-${rootProject.version}.jar"
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
	options.compilerArgs.addAll(
		listOf(
			"-Xlint:unchecked",
			"-Xlint:cast",
			"-Xlint:rawtypes",
			"-Xlint:overloads",
			"-Xlint:divzero",
			"-Xlint:finally",
			"-Xlint:static",
			"-Werror"
		)
	)
}

tasks.withType<ProcessResources> {
	from("src/main/java") {
		include(
			listOf(
				"**/*.properties",
				"**/*.yml",
				"**/*.yaml",
				"**/*.toml",
				"**/*.conf",
				"**/*.xml",
				"**/*.json",
				"**/*.pem",
				"**/*.p12",
				"**/*.pfx",
			)
		)
		exclude("**/.DS_Store")
		exclude("**/.gitkeep")
	}
}

//configurations.all {
//	exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
//}
