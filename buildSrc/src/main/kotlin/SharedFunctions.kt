/* =====================================================================================================================
 * 构建逻辑的共享函数
 * =================================================================================================================== */
import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
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
