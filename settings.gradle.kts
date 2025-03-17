pluginManagement {
	repositories {
		mavenLocal()
		gradlePluginPortal()

		file("buildSrc/config/maven/repositories.txt")
			.readLines(Charsets.UTF_8)
			.forEach { line ->
				if (line.isNotBlank()) {
					maven { url = uri(line.trim()); isAllowInsecureProtocol = true }
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
				if (line.isNotBlank()) {
					maven { url = uri(line.trim()); isAllowInsecureProtocol = true }
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
