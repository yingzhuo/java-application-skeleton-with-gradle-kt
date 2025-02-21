package com.mycompany.myproject.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@ImportResource("classpath*:config/*.groovy")
public class ApplicationBoot {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBoot.class, args);
	}

}
