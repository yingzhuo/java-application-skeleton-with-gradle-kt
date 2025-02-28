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
		mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.3")
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
			// "-Werror" 这有点过于严格了
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

tasks.named("build") {
	finalizedBy("postBuild")
}

tasks.register<Delete>("postBuild") {
	group = "build"
	description = "Cleanup mess of build task"

	enabled = getConfig(project, "project.build.cleanupAfterBuild").toBoolean()

	delete(
		layout.buildDirectory.dir("classes"),
		layout.buildDirectory.dir("generated"),
		layout.buildDirectory.dir("resources"),
		layout.buildDirectory.dir("tmp"),
		layout.buildDirectory.file("resolvedMainClassName"),
	)
}
