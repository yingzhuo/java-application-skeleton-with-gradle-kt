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
package buildlogic.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction
import java.io.Serializable
import javax.inject.Inject

open class LicensePlugin : Plugin<Project> {

	override fun apply(project: Project) {
		project.extensions.add(AddLicenseHeaderTask.TASK_NAME, PluginConfig(javaHeader = null))
		project.tasks.register(AddLicenseHeaderTask.TASK_NAME, AddLicenseHeaderTask::class.java, project)
	}

	open class AddLicenseHeaderTask @Inject constructor(private val project: Project) : DefaultTask() {
		companion object {
			const val TASK_NAME: String = "addLicenseHeader"
		}

		init {
			this.group = "license"
			this.description = "Adds a license header for source codes"
		}

		@TaskAction
		fun execute() {
			val config = this.project.extensions.getByName(TASK_NAME) as PluginConfig
			var javaHeader = config.javaHeader

			if (javaHeader.isNullOrBlank()) {
				throw StopExecutionException("配置错误 javaHeader缺失")
			}

			if (!javaHeader.endsWith("\n")) {
				javaHeader += "\n";
			}

			project.fileTree(project.rootDir) {
				include(
					"**/*.java",
					"**/*.groovy",
					"**/*.kt",
					"**/*.scala",
					"buildSrc/**/*.gradle",
					"buildSrc/**/*.gradle.kts"
				)
			}.forEach { file ->
				var content = file.readText()
				var changed = false
				if (!content.startsWith(javaHeader)) {
					content = javaHeader + content
					changed = true
				}

				if (!content.endsWith("\n")) {
					content += "\n"
					changed = true
				}

				if (changed) {
					file.writeText(content)
				}
			}
		}
	}

	open class PluginConfig(
		var javaHeader: String?
	) : Serializable

}
