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
setOf("projects-application", "projects-library").forEach {
	val dir = file(it)

	// 目录有误
	if (!dir.exists() || !dir.isDirectory) {
		throw GradleException("'${dir.name}' is NOT exists or NOT a directory")
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
