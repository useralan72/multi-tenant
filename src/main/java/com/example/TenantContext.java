package com.example;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class TenantContext {
    private static ThreadLocal<Object> currentTenant = new ThreadLocal<>();

    public Map<Object, Object> getResolvedDataSources() {
        return resolvedDataSources;
    }

    public void setResolvedDataSources(Map<Object, Object> resolvedDataSources) {
        this.resolvedDataSources = resolvedDataSources;
    }

    private Map<Object,Object> resolvedDataSources = new HashMap<>();

    public static void setCurrentTenant(Object tenant) {
        currentTenant.set(tenant);
    }

    public static Object getCurrentTenant() {
        return currentTenant.get();
    }
}
