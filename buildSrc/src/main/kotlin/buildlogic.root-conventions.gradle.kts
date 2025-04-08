/* =====================================================================================================================
 * 根项目专用构建逻辑，其他子项目严禁使用本逻辑
 * =================================================================================================================== */
plugins {
	id("base")
}

tasks.named<Wrapper>("wrapper") {
	// 国内镜像两个比较好的选择
	// (1) 腾讯云: https://mirrors.cloud.tencent.com/gradle/gradle-${gradleVersion}-bin.zip
	// (2) 华为云: https://mirrors.huaweicloud.com/gradle/gradle-${gradleVersion}-bin.zip
	distributionUrl = "https://mirrors.cloud.tencent.com/gradle/gradle-${ext.get("gradleWrapperVersion")}-bin.zip"
}

tasks.register<Delete>("removeWrapper") {
	group = "build setup"
	description = "Remove Gradle wrapper files."

	isFollowSymlinks = true

	delete(fileTree(rootDir) {
		include("gradlew")
		include("gradlew.bat")
		include("gradle/wrapper/")
	})
}

tasks.register<Exec>("github") {
	group = "github"
	description = "Commit and push codes to github."

	workingDir("$rootDir")
	commandLine("bash", "$rootDir/buildSrc/config/github/push.sh")
}

tasks.named("clean") {
	mustRunAfter(getLeafProjectNames(rootProject).map { "${it}:clean" })

	delete(fileTree(rootDir) {
		include(".kotlin/")
		include("buildSrc/.kotlin/")
		include("**/.DS_Store")
		include("**/*.log")
	})
}
