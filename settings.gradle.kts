pluginManagement {
	repositories {
		mavenLocal()
		gradlePluginPortal()

		file("buildSrc/config/maven/repositories.txt")
			.readLines(Charsets.UTF_8)
			.forEach { line ->
				val mirror = line.trim()
				if (mirror.isNotBlank() && !mirror.startsWith("#")) {
					maven { url = uri(mirror); isAllowInsecureProtocol = true }
				}
			}

		mavenCentral()
		google()
	}
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	repositories {
		mavenLocal()

		file("buildSrc/config/maven/repositories.txt")
			.readLines(Charsets.UTF_8)
			.forEach { line ->
				val mirror = line.trim()
				if (mirror.isNotBlank() && !mirror.startsWith("#")) {
					maven { url = uri(mirror); isAllowInsecureProtocol = true }
				}
			}

		mavenCentral()
		google()
	}

	repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
}

// root project
rootProject.name = "java-application-skeleton-with-gradle-kt"

// sub-projects
includeSubproject(file("projects-application"))
includeSubproject(file("projects-library"))

// tools
private fun includeSubproject(dir: File) {
	if (!dir.isDirectory) {
		return
	}

	dir.listFiles()?.forEach { subDir ->
		if (subDir.isDirectory) {
			for (subSubFile in subDir.listFiles() ?: emptyArray()) {
				if (subSubFile.isFile || subSubFile.name == "build.gradle.kts") {
					include("${dir.name}:${subDir.name}")
				}
			}
		}
	}
}
