package com.helios.rebate

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@ServletComponentScan
//@MapperScan(value = ["com.helios.rebate.domain.mapper"])
class Application

fun main(vararg args: String) {
    SpringApplication.run(Application::class.java, *args)
}