import java.io.FileFilter

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

// 子项目
includeSubprojects(
	file("projects-application"),
	file("projects-library")
)

// ---------------------------------------------------------------------------------------------------------------------

private fun includeSubprojects(vararg directories: File): Unit {
	val dirPredicate = FileFilter { f -> f.isDirectory }

	for (dir in directories) {
		dir.listFiles(dirPredicate)?.forEach { subFile ->
			include("${dir.name}:${subFile.name}")
		}
	}
}
