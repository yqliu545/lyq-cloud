package com.ruoyi.common.social.config;

import cn.hutool.extra.spring.SpringUtil;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.social.config.properties.SocialProperties;
import com.ruoyi.common.social.utils.AuthRedisStateCache;
import me.zhyd.oauth.cache.AuthStateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Social 配置属性
 * @author thiszhc
 */
@AutoConfiguration
@EnableConfigurationProperties(SocialProperties.class)
public class SocialAutoConfiguration {

    @Autowired
    private RedisService redisService;

    @Bean
    public AuthStateCache authStateCache() {
        return new AuthRedisStateCache(redisService);
    }

}
