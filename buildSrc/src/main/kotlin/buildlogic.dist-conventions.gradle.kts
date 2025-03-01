/* =====================================================================================================================
 * 发布用构建逻辑
 * =================================================================================================================== */

plugins {
	id("base")
}

ext {
	set("distFilename", "dist-${project.name}-${rootProject.version}.tar.gz")
}

tasks.register<Copy>("preDistCopy") {
	dependsOn("build")

	group = "build"
	description = "Copy files to dist-directory"

	from(layout.buildDirectory.dir("libs")) {
		include("**/*.jar")
		into("lib")
		rename("(.*).jar", "${project.name}-${getTimestamp("yyyyMMddHHmm")}.jar")
	}

	from("src/main/dist") {
		include("**/*.*")
		exclude("**/.DS_Store")
		exclude("**/.gitkeep")
	}

	into(layout.buildDirectory.dir("dist"))
}

tasks.register<Tar>("preDistCompression") {
	dependsOn("preDistCopy")

	group = "build"
	description = "Compress files of dist-directory"

	from(layout.buildDirectory.dir("dist")) {
		include("**/*.*")
		exclude("**/.DS_Store")
		exclude("**/.gitkeep")
	}

	compression = Compression.GZIP
	destinationDirectory = file(layout.buildDirectory)
	archiveFileName = project.extra.get("distFilename") as String
}

tasks.register<Copy>("dist") {
	dependsOn("preDistCompression")
	finalizedBy("postDist")

	group = "build"
	description = "Copy dist-file to root project build directory"

	from(layout.buildDirectory) {
		include(project.extra.get("distFilename") as String)
	}

	destinationDir = file("${rootDir}/build")
}

tasks.register<Delete>("postDist") {
	group = "build"
	description = "Cleanup mess of dist task"

	enabled = getConfig(project, "project.build.cleanupAfterDist").toBoolean()

	delete(
		layout.buildDirectory.dir("libs"),
		layout.buildDirectory.dir("dist"),
		layout.buildDirectory.file(project.extra.get("distFilename") as String)
	)

}
