package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
open class DemoApplication {

	fun main(args:Array<String>) {
		runApplication<DemoApplication>(*args)
	}
}