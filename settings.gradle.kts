import java.io.FileFilter

pluginManagement {
	repositories {
		mavenLocal()
		gradlePluginPortal()
		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
		maven { name = "腾讯云"; url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
		maven { name = "华为云"; url = uri("https://repo.huaweicloud.com/repository/maven/") }
		maven { name = "网易云"; url = uri("https://mirrors.163.com/maven/repository/maven-public/") }
		mavenCentral()
		google()
	}
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	repositories {
		mavenLocal()
		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
		maven { name = "腾讯云"; url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
		maven { name = "华为云"; url = uri("https://repo.huaweicloud.com/repository/maven/") }
		maven { name = "网易云"; url = uri("https://mirrors.163.com/maven/repository/maven-public/") }
		maven { name = "Spring(GA)"; url = uri("https://repo.spring.io/release") }
		maven { name = "Spring(Milestone)"; url = uri("https://repo.spring.io/milestone") }
		maven { name = "Spring(Snapshot)"; url = uri("https://repo.spring.io/snapshot") }
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
