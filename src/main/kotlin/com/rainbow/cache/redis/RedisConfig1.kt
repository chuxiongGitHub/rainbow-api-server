package com.rainbow.cache.redis

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import java.lang.StringBuilder

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
class RedisConfig1 : CachingConfigurerSupport() {
    override fun cacheManager(): CacheManager {
        return super.cacheManager()
    }

    override fun keyGenerator(): KeyGenerator {
        val sb = StringBuilder()
        return KeyGenerator { any, method, arrayOfAnys ->
            sb.append(any.javaClass.name)
            sb.append(method.name)
            for (obj in arrayOfAnys) {
                sb.append(obj.toString())
            }
        }
    }

    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, String> {
        val template= StringRedisTemplate(factory)

        val jackson2JsonRedisSerializer= Jackson2JsonRedisSerializer(Any::class.java)
        val om= ObjectMapper()
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)

        jackson2JsonRedisSerializer.setObjectMapper(om)

        template.valueSerializer
        template.afterPropertiesSet()

        return template

    }
}