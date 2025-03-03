/*
 *
 * Copyright 2022-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
@file:Suppress("UnstableApiUsage")

pluginManagement {
	repositories {
		mavenLocal()
		gradlePluginPortal()
		maven { name = "腾讯云"; url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
		maven { name = "华为云"; url = uri("https://repo.huaweicloud.com/repository/maven/") }
		maven { name = "网易云"; url = uri("https://mirrors.163.com/maven/repository/maven-public/") }
		mavenCentral()
		google()
	}
}

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}

	repositories {
		mavenLocal()
		maven { name = "腾讯云"; url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
		maven { name = "阿里云"; url = uri("https://maven.aliyun.com/repository/public/") }
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
