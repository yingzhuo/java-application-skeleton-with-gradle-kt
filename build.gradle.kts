plugins {
	id("buildlogic.root-conventions")
	id("buildlogic.license")
}

ext {
	// SpringBoot BOM 版本微调
	// https://docs.spring.io/spring-boot/appendix/dependency-versions/coordinates.html
	// https://docs.spring.io/spring-boot/appendix/dependency-versions/properties.html
	// set("xxx", "v1.0.0")
}

defaultTasks("distZip")

allprojects {
	group = "com.mycompany.myproject"
	version = "1.0.0"
}

addLicenseHeader {
	javaHeader = """
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
	""".trimIndent()
}
