package com.javaExample.Project.crud.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class CustomCacheErrorHandler implements CacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {

    }

    @Override
    public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {

    }

    @Override
    public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {

    }

    @Override
    public void handleCacheClearError(RuntimeException e, Cache cache) {

    }
}
