/* =====================================================================================================================
 * 构建逻辑的共享函数
 * =================================================================================================================== */

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
 * 获取gradle.properties文件配置
 */
fun getGradleProperty(
	project: Project,
	propertyName: String,
	defaultPropertyValue: String = "<no value>"
): String {
	return project.providers.gradleProperty(propertyName).orElse(defaultPropertyValue).get()
}

/**
 * 获取gradle.properties文件配置 (boolean类型)
 */
fun getGradlePropertyAsBoolean(
	project: Project,
	propertyName: String,
	defaultValue: Boolean = false
): Boolean {
	return getGradleProperty(project, propertyName, defaultValue.toString()).toBoolean()
}

/**
 * 获取环境变量
 */
fun getEnv(name: String, defaultValue: String = "<no value>"): String {
	return Optional.ofNullable(System.getenv(name))
		.orElse(defaultValue)
}

/**
 * 获取环境变量 (boolean类型)
 */
fun getEnvAsBoolean(name: String, defaultValue: Boolean = false): Boolean {
	return getEnv(name, defaultValue.toString()).toBoolean()
}
