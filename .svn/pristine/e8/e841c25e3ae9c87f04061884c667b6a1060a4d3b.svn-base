package com.cssl.cpf.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.cssl.cpf.domain.Emp;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig implements CachingConfigurer {

    /**
     * 创建并配置一个RedisTemplate实例
     * 该方法通过Spring的依赖注入机制，使用提供的RedisConnectionFactory来初始化RedisTemplate
     * 主要用于操作Redis数据库，执行键值对的存储和提取操作
     *
     * @param factory Redis连接工厂，用于建立与Redis服务器的连接
     * @return RedisTemplate<String, Emp> 一个配置好的RedisTemplate实例，用于操作键类型为String，值类型为Emp的Redis数据
     */
    @Bean(name = "template")
    public RedisTemplate<String, Emp> getTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Emp> redisTemplate = new RedisTemplate<String, Emp>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }


    /**
     * 创建并配置一个RedisTemplate实例
     * 该实例用于与Redis数据库进行交互，支持键值存储操作
     *
     * @param factory Redis连接工厂，用于建立Redis连接
     * @return 配置好的RedisTemplate实例，用于执行Redis操作
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        // 创建RedisTemplate实例，并设置连接工厂
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

        // 创建ObjectMapper实例，用于JSON序列化和反序列化
        ObjectMapper om = new ObjectMapper();
        // 设置ObjectMapper的可见性，以确保所有属性都能被序列化和反序列化
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 激活默认类型检测，以支持多态序列化和反序列化
        om.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);

        // 创建Jackson2JsonRedisSerializer实例，用于Redis中数据的序列化和反序列化
        Jackson2JsonRedisSerializer js = new Jackson2JsonRedisSerializer(om,Object.class);
        //springboot3+版本该方法过期，可以在上面构造方法中加入 om 参数
        //new Jackson2JsonRedisSerializer(om,Users.class);

        // 设置key的序列化类型为StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化类型为Jackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(js);
        // 返回配置好的RedisTemplate实例
        return redisTemplate;
    }

    /**
     * 缓存管理器
     * @param connectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        System.out.println("CacheManager......");
        //改序列化方式为json
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(om,Object.class);
        //springboot3+版本该方法过期，可以在上面构造方法中加入 om 参数


        //设置缓存配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        RedisCacheConfiguration config1 = config.serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .entryTtl(Duration.ofSeconds(300))   //缓存时间,秒
                .disableCachingNullValues()         //不缓存空值
                .prefixCacheNameWith("emp");         //前缀userssss+#id(userssss2)

        RedisCacheConfiguration config2 = config.serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .entryTtl(Duration.ofHours(1))   //缓存时间,小时
                .disableCachingNullValues()         //不缓存空值
                .prefixCacheNameWith("news");  //缓存区间prefixCacheNameWith

        //对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("emp", config1);     //user匹配@Cacheable的value属性值user
        configMap.put("news", config2);

        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, config, configMap);
        return cacheManager;
    }
}
