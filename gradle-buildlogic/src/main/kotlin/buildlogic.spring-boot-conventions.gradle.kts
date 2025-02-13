import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("java")
	id("org.springframework.boot")
	id("com.gorylenko.gradle-git-properties")
}

tasks.named<Jar>("jar") {
	enabled = false
}

tasks.named<BootJar>("bootJar") {
	enabled = true

	archiveFileName = "${project.name}-${rootProject.version}.jar"

	manifest {
		attributes(
			mapOf(
				"Implementation-Title" to project.name,
				"Implementation-Version" to rootProject.version,
				"Main-Class" to "org.springframework.boot.loader.launch.PropertiesLauncher",
				"Start-Class" to ext.get("bootMainClass") as String
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

tasks.named<DefaultTask>("build") {
	doLast {
		copy {
			from("${layout.buildDirectory.file("libs").get()}")
			into("${rootDir}/build")
			include("**/*.jar")
			exclude("**/*-sources.jar")
			exclude("**/*-javadoc.jar")
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
