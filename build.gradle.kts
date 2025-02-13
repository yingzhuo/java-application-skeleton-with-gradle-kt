plugins {
	id("base")
}

group = "com.mycompany.myproject"
version = "1.0.0"
description = "xxx"

tasks.named<Wrapper>("wrapper") {
	distributionUrl = "https://mirrors.cloud.tencent.com/gradle/gradle-8.12.1-bin.zip"
}
