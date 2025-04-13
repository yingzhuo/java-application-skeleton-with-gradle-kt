pluginManagement {
	repositories {
		mavenLocal()

		file("config/maven/repositories.txt")
			.readLines(Charsets.UTF_8)
			.forEach { line ->
				val mirror = line.trim()
				if (mirror.isNotBlank() && !mirror.startsWith("#")) {
					maven { url = uri(mirror); isAllowInsecureProtocol = true }
				}
			}

		gradlePluginPortal()
		mavenCentral()
		google()
	}
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}

	repositories {
		mavenLocal()
		gradlePluginPortal()

		file("config/maven/repositories.txt")
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
