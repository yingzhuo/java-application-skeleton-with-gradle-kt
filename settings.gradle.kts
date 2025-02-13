@file:Suppress("UnstableApiUsage")

pluginManagement {
	repositories {
		mavenLocal()
		gradlePluginPortal()
		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
		mavenCentral()
		google()
	}
}

dependencyResolutionManagement {
	repositories {
		mavenLocal()
		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
		maven { name = "阿里云(Spring)"; url = uri("https://maven.aliyun.com/repository/spring/") }
		maven { name = "Spring(GA)"; url = uri("https://repo.spring.io/release") }
		maven { name = "Spring(Milestone)"; url = uri("https://repo.spring.io/milestone") }
		maven { name = "Spring(Snapshot)"; url = uri("https://repo.spring.io/snapshot") }
		mavenCentral()
		google()
	}
}

rootProject.name = "java-application-skeleton-with-gradle-kt"

// 构建逻辑
includeBuild("gradle-buildlogic")

// 项目
include("projects-application:core")
include("projects-library:utility")
include("projects-library:springboot-starter")
