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
		mavenBom("org.springframework.boot:spring-boot-dependencies:${project.ext["springBootVersion"]}")
	}
}

java {
	sourceCompatibility = JavaVersion.toVersion("${project.ext["javaVersion"]}")
	targetCompatibility = JavaVersion.toVersion("${project.ext["javaVersion"]}")

	toolchain {
		languageVersion = JavaLanguageVersion.of("${project.ext["javaVersion"]}")
	}

	//withSourcesJar()
	//withJavadocJar()
}

tasks.named<Jar>("jar") {
	archiveFileName = "${project.name}-${project.version}.jar"
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
				"**/*.lua"
			)
		)
		exclude("**/.DS_Store")
		exclude("**/.gitkeep")
	}
}
