package buildlogic.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

open class LicensePlugin : Plugin<Project> {

	override fun apply(project: Project) {
		project.extensions.add(AddLicenseHeaderTask.TASK_NAME, Config(javaHeader = null))
		project.tasks.register(AddLicenseHeaderTask.TASK_NAME, AddLicenseHeaderTask::class.java, project)
	}

	open class AddLicenseHeaderTask @Inject constructor(private val project: Project) : DefaultTask() {
		companion object {
			const val TASK_NAME = "addLicenseHeader"
		}

		init {
			group = "license"
			description = "Adds a license header for source codes"
		}

		@TaskAction
		fun execute() {
			val config = this.project.extensions.getByName(TASK_NAME) as Config
			var javaHeader = config.javaHeader

			if (javaHeader.isNullOrBlank()) {
				throw StopExecutionException("配置错误 javaHeader缺失")
			}

			if (!javaHeader.endsWith("\n")) {
				javaHeader += "\n";
			}

			project.fileTree(project.rootDir) {
				include("**/*.java")
			}.forEach { file ->
				var content = file.readText()
				if (!content.startsWith(javaHeader)) {
					content = javaHeader + content
					file.writeText(content)
				}
			}
		}
	}

	open class Config(var javaHeader: String?)

}
