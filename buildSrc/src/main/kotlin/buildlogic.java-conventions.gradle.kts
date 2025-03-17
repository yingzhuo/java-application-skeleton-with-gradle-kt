/*
 *
 * Copyright 2022-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
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
		mavenBom("org.springframework.boot:spring-boot-dependencies:${project.ext["springBootVersion"]}") {
			bomProperties(mapOf())
		}
	}
}

java {
	sourceCompatibility = JavaVersion.toVersion("${project.ext["javaVersion"]}")
	targetCompatibility = JavaVersion.toVersion("${project.ext["javaVersion"]}")

	toolchain {
		languageVersion = JavaLanguageVersion.of("${project.ext["javaVersion"]}")
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
				"**/*.lua"
			)
		)
		exclude("**/.DS_Store")
		exclude("**/.gitkeep")
	}
}
