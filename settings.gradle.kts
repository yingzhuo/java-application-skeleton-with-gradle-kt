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

rootProject.name = "java-application-skeleton-with-gradle-kt"
includeSubproject(file("projects-application"))
includeSubproject(file("projects-library"))

// ---------------------------------------------------------------------------------------------------------------------

private fun includeSubproject(dir: File) {
	dir.listFiles()?.forEach { subDir ->
		if (subDir.isDirectory) {
			include("${dir.name}:${subDir.name}")
		}
	}
}
