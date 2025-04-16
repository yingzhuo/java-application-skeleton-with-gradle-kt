plugins {
	id("buildlogic.root-project-conventions")
}

ext {
	// SpringBoot BOM 版本微调
	// https://docs.spring.io/spring-boot/appendix/dependency-versions/coordinates.html
	// https://docs.spring.io/spring-boot/appendix/dependency-versions/properties.html
}

defaultTasks("distZip")

allprojects {
	group = "io.github.yingzhuo"
	version = "${ext["projectVersion"]}"
}
