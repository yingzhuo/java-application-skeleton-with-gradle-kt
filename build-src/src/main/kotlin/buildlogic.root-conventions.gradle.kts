/* =====================================================================================================================
 * 根项目专用构建逻辑，其他子项目严禁使用本逻辑
 * =================================================================================================================== */

plugins {
	id("base")
}

tasks.named<Wrapper>("wrapper") {
	distributionUrl = getGradleProperty(project, "project.gradle-wrapper.distributionUrl")
}

tasks.named("clean") {
	mustRunAfter(getLeafProjectNames(rootProject).map { "${it}:clean" })
	delete(layout.projectDirectory.dir("build-src/.kotlin"))
}

tasks.register<DefaultTask>("addJavaLicenseHeader") {

	var header = file(layout.projectDirectory.file(".license_headers/java.txt")).readText()
	if (!header.endsWith("\n")) {
		header += "\n"
	}

	fileTree(layout.projectDirectory) {
		include("**/*.java")
	}.forEach { file ->
		val content = file.readText()
		if (!content.startsWith(header)) {
			file.writeText(header + content)
		}
	}

}
