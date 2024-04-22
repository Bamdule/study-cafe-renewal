package io.spring.studycafe.presentation.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/redis-test")
@RestController
public class TestRedisController {
    private final RedisTemplate<String, String> redisTemplate;

    public TestRedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public ResponseEntity<Object> find(String key, String hashKey) {
        String string = (String) redisTemplate.opsForHash().get(key, hashKey);
        return ResponseEntity.ok(string);
    }

    @PostMapping
    public ResponseEntity<Object> save(String key, String hashKey, String value) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, hashKey, value);
        return ResponseEntity.ok(value);
    }

}
