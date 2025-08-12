package com.example.demo.redis_cache.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisCacheServiceImpl implements RedisCacheService {

    final private StringRedisTemplate redisTemplate;

    @Override
    public <K, V> void setKeyAndValue(K key, V value) {
        setKeyAndValue(key, value, Duration.ofMinutes(720));
    }

    @Override
    public <K, V> void setKeyAndValue(K key, V value, Duration timeToLive) {
        String keyAsString = String.valueOf(key);
        String valueAsString = String.valueOf(value);

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(keyAsString, valueAsString, timeToLive);
    }

    @Override
    public <T> T getValueByKey(String key, Class<T> clazz) {
        // for redis 연산..
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        if (value == null) {
            return null;
        }
        if (clazz == String.class) {
            return clazz.cast(value);
        }
        if (clazz == Integer.class) {
            return clazz.cast(Integer.valueOf(value));
        }
        if (clazz == Long.class) {
            return clazz.cast(Long.valueOf(value));
        }
        throw new IllegalArgumentException("Unsupported type: " + clazz);
    }

    @Override
    public void deleteByKey(String userToken) {
        redisTemplate.delete(userToken);
    }
}
