/*
 *
 * Copyright 2022-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
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
	distributionUrl = "https://mirrors.cloud.tencent.com/gradle/gradle-${gradleVersion}-bin.zip"
}

tasks.register<Exec>("github") {
	workingDir("$rootDir")
	commandLine("bash", "$rootDir/buildSrc/config/github/push.sh")
}

tasks.named("clean") {
	mustRunAfter(getLeafProjectNames(rootProject).map { "${it}:clean" })
	delete(
		layout.projectDirectory.dir(".kotlin"),
		layout.projectDirectory.dir("buildSrc/.kotlin")
	)
}
