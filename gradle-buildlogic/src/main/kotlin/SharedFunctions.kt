import org.gradle.api.Project

object SharedFunctions {

	fun getLeafProjectNames(rootProject: Project): List<String> {
		val list = mutableListOf<String>()
		for (level1 in rootProject.subprojects) {
			for (level2 in level1.subprojects) {
				list.add(":${level1.name}:${level2.name}")
			}
		}
		return list.toList()
	}

	fun getGradleProperty(
		project: Project,
		propertyName: String,
		defaultPropertyValue: String = "<no value>"
	): String? {
		return project.providers.gradleProperty(propertyName).orElse(defaultPropertyValue).get()
	}

}
