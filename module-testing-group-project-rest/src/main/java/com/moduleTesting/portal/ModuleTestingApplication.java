package com.moduleTesting.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication incapsulate a lots of component like - @ComponentScan, @EnableAutoConfiguration,
 * jpa-repositories, json-serialization and so on. And he places @ComponentScan in com.*******.space package.
 * This package com.*******.space must be common for all modules.
 * You have to have in all packages - the same parent part. For example - com.jetMinds.nextLevel
 */
//@SpringBootApplication(scanBasePackages={"com.moduleTesting.portal","com.moduleTesting.service","com.moduleTesting.portal.repository","com.moduleTesting.portal.entity"})
//@EnableJpaRepositories("com.moduleTesting.portal.repository")
@SpringBootApplication
public class ModuleTestingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleTestingApplication.class, args);
    }

}
