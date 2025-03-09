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
 * SpringBoot应用程序构建逻辑
 * =================================================================================================================== */
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("java")
	id("distribution")
	id("org.springframework.boot")
	id("com.gorylenko.gradle-git-properties")
}

tasks.named<Jar>("jar") {
	enabled = false
}

tasks.named<BootJar>("bootJar") {

	archiveFileName = "${project.name}-${rootProject.version}.jar"

	manifest {
		attributes(
			mapOf(
				"Main-Class" to "org.springframework.boot.loader.launch.PropertiesLauncher",
			)
		)
	}

	includeTools = false

	layered {
		enabled = true

		application {
			intoLayer("spring-boot-loader") {
				include("org/springframework/boot/loader/**")
			}
			intoLayer("application")
		}

		dependencies {
			intoLayer("application") {
				includeProjectDependencies()
			}

			intoLayer("snapshot-dependencies") {
				include("*:*:*SNAPSHOT")
			}

			intoLayer("dependencies")
		}

		layerOrder = listOf(
			"dependencies",
			"spring-boot-loader",
			"snapshot-dependencies",
			"application"
		)
	}

	setExcludes(
		setOf(
			"**/.gitkeep",
			"**/.DS_Store",
			"**/netty-*-macos*.jar",
			"**/netty-*-osx*.jar",
		)
	)
}

tasks.withType<BootBuildImage> {
	enabled = false
}

springBoot {
	buildInfo {
		properties {
			group.set(rootProject.group.toString())
			artifact.set(project.name)
			version.set(rootProject.version.toString())

			additional.set(
				mapOf(
					"author" to "应卓"
				)
			)

			excludes.set(setOf("name"))
		}
	}
}

gitProperties {
	keys = listOf("git.branch", "git.commit.id", "git.commit.id.abbrev", "git.commit.time", "git.dirty")
	dateFormat = "yyyy-MM-dd HH:mm:ss.SSS"
	dateFormatTimeZone = "Asia/Shanghai"
	failOnNoGitDirectory = false
	gitPropertiesName = "git.properties"
}

distributions {
	named("main") {
		distributionBaseName = project.name
		contents {
			from(tasks.bootJar) {
				into("lib")
				rename { fileName ->
					fileName.replace("-${project.version}", "")
				}
			}

			from(rootDir) {
				include("README.*")
			}
		}
	}
}

tasks.named("distTar") {
	enabled = false
}
