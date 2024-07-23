package com.sql.datax.admin.core.util;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * local cache tool
 */
public class LocalCacheUtil {

    private static final ConcurrentMap<String, LocalCacheData> cacheRepository = new ConcurrentHashMap<>();   // 类型建议用抽象父类，兼容性更好；

    @Setter
    @Getter
    private static class LocalCacheData {
        private String key;
        private Object val;
        private long timeoutTime;

        public LocalCacheData() {
        }

        public LocalCacheData(String key, Object val, long timeoutTime) {
            this.key = key;
            this.val = val;
            this.timeoutTime = timeoutTime;
        }
    }


    /**
     * set cache
     */
    public static boolean set(String key, Object val, long cacheTime) {
        // clean timeout cache, before set new cache (avoid cache too much)
        cleanTimeoutCache();

        // set new cache
        if (key == null || key.trim().isEmpty()) {
            return false;
        }
        if (val == null) {
            remove(key);
        }
        if (cacheTime <= 0) {
            remove(key);
        }
        long timeoutTime = System.currentTimeMillis() + cacheTime;
        LocalCacheData localCacheData = new LocalCacheData(key, val, timeoutTime);
        cacheRepository.put(localCacheData.getKey(), localCacheData);
        return true;
    }

    /**
     * remove cache
     */
    public static boolean remove(String key) {
        if (key == null || key.trim().isEmpty()) {
            return false;
        }
        cacheRepository.remove(key);
        return true;
    }

    /**
     * get cache
     */
    public static Object get(String key) {
        if (key == null || key.trim().isEmpty()) {
            return null;
        }
        LocalCacheData localCacheData = cacheRepository.get(key);
        if (localCacheData != null && System.currentTimeMillis() < localCacheData.getTimeoutTime()) {
            return localCacheData.getVal();
        } else {
            remove(key);
            return null;
        }
    }

    /**
     * clean timeout cache
     */
    public static void cleanTimeoutCache() {
        if (!cacheRepository.keySet().isEmpty()) {
            for (String key : cacheRepository.keySet()) {
                LocalCacheData localCacheData = cacheRepository.get(key);
                if (localCacheData != null && System.currentTimeMillis() >= localCacheData.getTimeoutTime()) {
                    cacheRepository.remove(key);
                }
            }
        }
    }

}
