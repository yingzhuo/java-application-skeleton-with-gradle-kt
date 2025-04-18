/* =====================================================================================================================
 * Docker镜像相关构建逻辑
 * =================================================================================================================== */
plugins {
	id("base")
}

tasks.register<Copy>("prepareDockerContext") {
	group = "docker"
	description = "prepare docker context directory."

	dependsOn("assemble")

	from("src/main/docker") {
		include("**/*")
	}

	from(layout.buildDirectory.dir("libs")) {
		include("**/*.jar")
		exclude("**/*-javadoc.jar", "**/*-sources.jar")
	}

	into(layout.buildDirectory.dir("docker-context"))
}

tasks.register<Exec>("buildDockerImage") {
	group = "docker"
	description = "build docker image."

	dependsOn("prepareDockerContext")
	finalizedBy("cleanPrepareDockerContext")

	commandLine("bash", "$rootDir/buildSrc/config/docker/build-image.sh", "${project.ext["dockerTag"]}")
	workingDir(layout.buildDirectory.dir("docker-context"))
}

tasks.register<Exec>("removeDockerImage") {
	group = "docker"
	description = "remove docker image."

	commandLine("bash", "$rootDir/buildSrc/config/docker/remove-image.sh", "${project.ext["dockerTag"]}")
}
