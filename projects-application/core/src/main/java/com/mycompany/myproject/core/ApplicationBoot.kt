package com.mycompany.myproject.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource

@ImportResource("classpath:config/groovy-dsl.groovy")
@SpringBootApplication
open class ApplicationBoot

fun main(args: Array<String>) {
	println(Constants1.HELLO)
	runApplication<ApplicationBoot>(*args)
}
