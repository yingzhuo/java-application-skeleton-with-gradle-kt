@file:Suppress("UnstableApiUsage")

pluginManagement {
	repositories {
		mavenLocal()
		gradlePluginPortal()
		maven { name = "腾讯云"; url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
		maven { name = "华为云"; url = uri("https://repo.huaweicloud.com/repository/maven/") }
		mavenCentral()
		google()
	}
}

dependencyResolutionManagement {
	repositories {
		mavenLocal()
//		maven { name = "腾讯云"; url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
//		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
//		maven { name = "华为云"; url = uri("https://repo.huaweicloud.com/repository/maven/") }
//		maven { name = "Spring(GA)"; url = uri("https://repo.spring.io/release") }
//		maven { name = "Spring(Milestone)"; url = uri("https://repo.spring.io/milestone") }
//		maven { name = "Spring(Snapshot)"; url = uri("https://repo.spring.io/snapshot") }
		mavenCentral()
		google()
	}

	repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
}

rootProject.name = "java-application-skeleton-with-gradle-kt"

// 构建逻辑
includeBuild("build-src")

// 项目
include("projects-application:core")
include("projects-library:utility")
include("projects-library:springboot-starter")
