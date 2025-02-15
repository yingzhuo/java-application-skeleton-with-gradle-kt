/* =====================================================================================================================
 * 根项目专用构建逻辑，其他子项目严禁使用本逻辑
 * =================================================================================================================== */

plugins {
	id("base")
}

tasks.named<Wrapper>("wrapper") {
	distributionUrl = getGradleProperty(project, "project.gradle-wrapper.distributionUrl")
}

tasks.register<Zip>("zipJar") {
	group = "build"
	description = "Zip jars"

	enabled = getGradlePropertyAsBoolean(project, "project.building.zipAfterBuilding")

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

tasks.register<DefaultTask>("deleteJar") {
	group = "build"
	description = "Delete jar"

	enabled = getGradlePropertyAsBoolean(project, "project.building.deleteJarAfterZipping")

	doLast {
		delete(
			fileTree(layout.buildDirectory) {
				include("**/*.jar")
				exclude("**/*.zip")
			}
		)
	}
}

tasks.named("build") {
	val prevTasks = mutableSetOf<String>()
	prevTasks.addAll(getLeafProjectNames(project).stream().map { "$it:build" }.toList())

	// 保证 :build 最后运行
	mustRunAfter(prevTasks)
	finalizedBy("zipJar", "deleteJar")
}

tasks.named("clean") {
	val prevTasks = mutableSetOf<String>()
	prevTasks.addAll(getLeafProjectNames(project).stream().map { "$it:clean" }.toList())

	// 保证 :clean 最后运行
	mustRunAfter(prevTasks)

	doLast {
		delete(
			"gradle/.kotlin",
		)
	}
}
