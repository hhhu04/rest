package com.rest.open.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MainController {

    private final val KEY = "a6lMmhOKDVCu32OBaCI8eyZ989gDBrc2"


    @GetMapping("/open")
    fun test(): String {

        return KEY
    }



}