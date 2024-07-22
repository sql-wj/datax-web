package com.sql.datax.rpc.registry.impl;

import com.sql.datax.rpc.registry.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * service registry for "local"
 */
public class LocalServiceRegistry extends ServiceRegistry {

    /**
     * registry data
     */
    private Map<String, TreeSet<String>> registryData;

    /**
     * @param param ignore, not use
     */
    @Override
    public void start(Map<String, String> param) {
        registryData = new HashMap<>();
    }

    @Override
    public void stop() {
        registryData.clear();
    }

    @Override
    public boolean registry(Set<String> keys, String value) {
        if (keys == null || keys.isEmpty() || value == null || value.trim().isEmpty()) {
            return false;
        }
        for (String key : keys) {
            TreeSet<String> values = registryData.computeIfAbsent(key, k -> new TreeSet<>());
            values.add(value);
        }
        return true;
    }

    @Override
    public boolean remove(Set<String> keys, String value) {
        if (keys == null || keys.isEmpty() || value == null || value.trim().isEmpty()) {
            return false;
        }
        for (String key : keys) {
            TreeSet<String> values = registryData.get(key);
            if (values != null) {
                values.remove(value);
            }
        }
        return true;
    }

    @Override
    public Map<String, TreeSet<String>> discovery(Set<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return null;
        }
        Map<String, TreeSet<String>> registryDataTmp = new HashMap<>();
        for (String key : keys) {
            TreeSet<String> valueSetTmp = discovery(key);
            if (valueSetTmp != null) {
                registryDataTmp.put(key, valueSetTmp);
            }
        }
        return registryDataTmp;
    }

    @Override
    public TreeSet<String> discovery(String key) {
        return registryData.get(key);
    }

}
