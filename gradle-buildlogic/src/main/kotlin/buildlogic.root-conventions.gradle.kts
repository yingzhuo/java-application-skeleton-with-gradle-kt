/* =====================================================================================================================
 * 跟项目专用构建逻辑，其他子项目严禁使用本逻辑
 * =================================================================================================================== */

plugins {
	id("base")
}

tasks.named<Wrapper>("wrapper") {
	distributionUrl = SharedFunctions.getGradleProperty(project, "project.gradle-wrapper.distributionUrl")
}

tasks.register<Delete>("uninstallWrapper") {
	delete(
		"${rootProject.rootDir}/gradle/wrapper",
		"${rootProject.rootDir}/gradlew",
		"${rootProject.rootDir}/gradlew.bat",
	)
}

tasks.register<Zip>("zipJar") {
	group = "build"
	description = "Zip jars"

	from(layout.projectDirectory) {
		include("README.md")
		include("LICENSE.txt")
	}

	from(layout.projectDirectory.file("build")) {
		into("lib")
		include("**/*.jar")
	}

	destinationDirectory = layout.projectDirectory.dir("build")
	archiveFileName = "${rootProject.name}-${rootProject.version}.zip"
}

tasks.named("build") {
	enabled = SharedFunctions.getGradleProperty(project, "project.building.zipAfterBuild", "false").toBoolean()

	mustRunAfter(
		SharedFunctions.getLeafProjectNames(project).stream().map { "$it:build" }.toList()
	)
	finalizedBy("zipJar")
}

tasks.named("clean") {
	mustRunAfter(
		SharedFunctions.getLeafProjectNames(project).stream().map { "$it:clean" }.toList()
	)

	doLast {
		delete(
			"gradle-buildlogic/.kotlin",
		)
	}
}
