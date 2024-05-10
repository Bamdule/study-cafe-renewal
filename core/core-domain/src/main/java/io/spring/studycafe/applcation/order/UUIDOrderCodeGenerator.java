package io.spring.studycafe.applcation.order;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDOrderCodeGenerator implements OrderCodeGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
