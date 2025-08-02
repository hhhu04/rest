package com.rest.open

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OpenApplication

fun main(args: Array<String>) {
    runApplication<OpenApplication>(*args)
}
