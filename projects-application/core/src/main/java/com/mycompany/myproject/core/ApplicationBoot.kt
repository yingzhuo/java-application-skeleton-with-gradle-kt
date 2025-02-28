package com.mycompany.myproject.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans

@SpringBootApplication
class ApplicationBoot

fun main(args: Array<String>) {
	runApplication<ApplicationBoot>(*args) {
		addInitializers(beans {
		})
	}
}
