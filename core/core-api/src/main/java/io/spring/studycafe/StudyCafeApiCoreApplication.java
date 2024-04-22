package io.spring.studycafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class StudyCafeApiCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyCafeApiCoreApplication.class, args);
    }

}
