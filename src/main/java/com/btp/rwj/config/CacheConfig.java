package com.btp.rwj.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching//开启缓存
public class CacheConfig {
    @Value("${static.cache.expire}")
    private int expire;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig(expire))//默认缓存时间
                .withInitialCacheConfigurations(iniCacheConfigMap())//自定义缓存配置
                .transactionAware()
                .build();
    }

    private RedisCacheConfiguration defaultCacheConfig(Integer second) {
        //序列化配置
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        serializer.setObjectMapper(mapper);
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(second))
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))//String序列化
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(serializer))//json序列化
                .disableCachingNullValues();
    }

    private Map<String, RedisCacheConfiguration> iniCacheConfigMap() {
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("material", defaultCacheConfig(60 * 60 * 24 * 7));
        configMap.put("warehouse", defaultCacheConfig(60 * 60 * 24 * 7));
        configMap.put("user", defaultCacheConfig(60 * 60 * 24 * 7));
        return configMap;
    }
}


