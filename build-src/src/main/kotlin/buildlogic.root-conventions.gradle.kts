/* =====================================================================================================================
 * 根项目专用构建逻辑，其他子项目严禁使用本逻辑
 * =================================================================================================================== */

plugins {
	id("base")
}

tasks.named<Wrapper>("wrapper") {
	val propertyName = "project.gradle-wrapper.distributionUrl"
	val url = getGradleProperty(project, propertyName, "<no value>")
	if (url == "<no value>") {
		throw StopExecutionException("配置问题: '$propertyName' 配置缺失")
	}
	distributionUrl = url
}

tasks.named("clean") {
	mustRunAfter(getLeafProjectNames(rootProject).map { "${it}:clean" })
	delete(layout.projectDirectory.dir("build-src/.kotlin"))
}
