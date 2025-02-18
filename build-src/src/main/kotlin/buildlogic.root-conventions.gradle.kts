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
	delete(layout.projectDirectory.dir("gradle/.kotlin"))

	if (getGradlePropertyAsBoolean(project, "project.clean.cleanBuildSourceDir", false)) {
		delete(layout.projectDirectory.dir("gradle/.gradle"))
		delete(layout.projectDirectory.dir("gradle/build"))
	}

}
