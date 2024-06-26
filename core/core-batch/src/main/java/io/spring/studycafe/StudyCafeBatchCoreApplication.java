package io.spring.studycafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConfigurationPropertiesScan
@EnableScheduling
@SpringBootApplication
public class StudyCafeBatchCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyCafeBatchCoreApplication.class, args);
    }

}
