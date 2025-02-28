package com.mycompany.myproject.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ApplicationBoot

fun main(args: Array<String>) {
	println(Constants1.HELLO)
	runApplication<ApplicationBoot>(*args)
}
