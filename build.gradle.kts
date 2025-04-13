plugins {
	id("buildlogic.root-conventions")
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
	version = project.ext["projectVersion"].toString()
}
