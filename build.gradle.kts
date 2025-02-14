plugins {
	id("buildlogic.root-conventions")
}

ext {
	// SpringBoot BOM 版本微调
	// https://docs.spring.io/spring-boot/appendix/dependency-versions/properties.html
	set("spring-framework.version", "6.2.3")
}

group = "com.mycompany.myproject"
version = "1.0.0"
description = "xxx"
