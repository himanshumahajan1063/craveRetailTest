package com.example.craveRetail.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import static java.util.Arrays.asList;

public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager mgr = new ConcurrentMapCacheManager();
        mgr.setCacheNames(asList("users"));
        return mgr;
    }
}
