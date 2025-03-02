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
plugins {
	`kotlin-dsl`
}

dependencies {
	val kotlinVersion = "1.9.25"
	implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:${kotlinVersion}")
	implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:${kotlinVersion}")
	implementation("org.jetbrains.kotlin.plugin.jpa:org.jetbrains.kotlin.plugin.jpa.gradle.plugin:${kotlinVersion}")
	implementation("org.jetbrains.kotlin.plugin.allopen:org.jetbrains.kotlin.plugin.allopen.gradle.plugin:${kotlinVersion}")

	implementation("io.spring.gradle:dependency-management-plugin:1.1.7")
	implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:2.4.2")
	implementation("org.springframework.boot:spring-boot-gradle-plugin:3.4.3")
}

gradlePlugin {
	plugins {
		create("LicensePlugin") {
			id = "buildlogic.license"
			implementationClass = "buildlogic.gradle.plugin.LicensePlugin"
		}
	}
}
