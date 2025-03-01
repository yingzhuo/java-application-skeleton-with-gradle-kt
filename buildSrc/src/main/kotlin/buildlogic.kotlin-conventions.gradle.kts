/* =====================================================================================================================
 * Kotlin项目构建逻辑
 * =================================================================================================================== */

import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
	kotlin("jvm")
	kotlin("plugin.allopen")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
}

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

sourceSets.main {
	java.srcDirs("src/main/java", "src/main/kotlin")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

tasks.withType<KotlinJvmCompile>().configureEach {
	jvmTargetValidationMode.set(JvmTargetValidationMode.ERROR)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
	}
}
