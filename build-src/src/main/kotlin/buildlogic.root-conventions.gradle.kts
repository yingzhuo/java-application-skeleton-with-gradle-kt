/* =====================================================================================================================
 * 根项目专用构建逻辑，其他子项目严禁使用本逻辑
 * =================================================================================================================== */

plugins {
	id("base")
}

tasks.named<Wrapper>("wrapper") {
	val url = "https://mirrors.cloud.tencent.com/gradle/gradle-${properties["project.gradle-wrapper.version"]}-bin.zip"
	distributionUrl = url
}

tasks.named("clean") {
	mustRunAfter(getLeafProjectNames(rootProject).map { "${it}:clean" })
	delete(layout.projectDirectory.dir("build-src/.kotlin"))
}
