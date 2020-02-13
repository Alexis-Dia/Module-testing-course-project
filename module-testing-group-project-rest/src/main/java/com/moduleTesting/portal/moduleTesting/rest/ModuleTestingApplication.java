package com.moduleTesting.portal.moduleTesting.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/*Temraary descicion cause iI don't why Spring con't find automatically this Maben module*/
//@ComponentScan("com.moduleTesting.*")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan("com.moduleTesting.portal.repository")
public class ModuleTestingApplication  {
    public static void main(String[] args) {
        SpringApplication.run(ModuleTestingApplication.class, args);
    }


}
