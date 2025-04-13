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

	archiveFileName = "${project.name}-${project.version}.jar"

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
	dotGitDirectory = file("$rootDir/.git/")
	keys = listOf("git.branch", "git.commit.id", "git.commit.id.abbrev", "git.commit.time", "git.dirty")
	dateFormat = "yyyy-MM-dd HH:mm:ss.SSS"
	dateFormatTimeZone = "Asia/Shanghai"
	gitPropertiesName = "git.properties"
	failOnNoGitDirectory = false
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

tasks.named("distZip") {
	enabled = true
}

tasks.named("distTar") {
	enabled = false
}
