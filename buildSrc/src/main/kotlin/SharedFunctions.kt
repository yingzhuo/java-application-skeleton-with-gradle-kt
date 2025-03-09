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
 * 构建逻辑的共享函数
 * =================================================================================================================== */

import org.gradle.api.GradleException
import org.gradle.api.Project
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private const val EMPTY_STR: String = ""
private const val DEFAULT_TIMESTAMP_FORMAT_PATTERN = "yyyyMMddHHmmss"

/**
 * 获取时间戳
 */
fun getTimestamp(formatPattern: String = DEFAULT_TIMESTAMP_FORMAT_PATTERN): String {
	return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatPattern))
}

/**
 * 获取叶子子项目名称 <br>
 * 格式 :sub-project:sub-sub-project
 */
fun getLeafProjectNames(rootProject: Project): SortedSet<String> {
	return rootProject.allprojects
		.filter {
			it.subprojects.isEmpty()
		}.map {
			it.displayName
				.replace("project '", EMPTY_STR)
				.replace("'", EMPTY_STR)
				.trim()
		}.toSortedSet()
}

/**
 * 尝试读取配置
 * gradle.properties | env
 */
fun getConfig(project: Project, name: String, defaultValue: String? = null): String {
	var value = project.properties[name]
	if (value == null) {
		value = System.getenv(name)
	}
	if (value == null) {
		value = defaultValue
	}
	if (value == null) {
		throw GradleException("\"$name\" has no value defined")
	}
	return value.toString()
}
