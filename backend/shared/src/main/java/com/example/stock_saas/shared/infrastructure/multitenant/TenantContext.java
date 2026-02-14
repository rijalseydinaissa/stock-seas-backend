package com.example.stock_saas.shared.infrastructure.multitenant;

import com.example.stock_saas.shared.domain.exception.TenantMismatchException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Thread-local storage for current tenant context.
 * This ensures tenant isolation at the application level.
 *
 * Usage:
 * 1. Set tenant context from JWT after authentication
 * 2. All database queries automatically filtered by tenant_id
 * 3. Clear context after request completion
 */
@Slf4j
public class TenantContext {

    private static final ThreadLocal<UUID> CURRENT_TENANT = new ThreadLocal<>();

    private TenantContext() {
        // Utility class
    }

    /**
     * Set the current tenant ID for this thread
     */
    public static void setTenantId(UUID tenantId) {
        if (tenantId == null) {
            throw new IllegalArgumentException("Tenant ID cannot be null");
        }
        log.debug("Setting tenant context: {}", tenantId);
        CURRENT_TENANT.set(tenantId);
    }

    /**
     * Get the current tenant ID
     * @throws TenantMismatchException if no tenant context is set
     */
    public static UUID getTenantId() {
        UUID tenantId = CURRENT_TENANT.get();
        if (tenantId == null) {
            throw new TenantMismatchException("No tenant context available");
        }
        return tenantId;
    }

    /**
     * Get the current tenant ID or null if not set
     */
    public static UUID getTenantIdOrNull() {
        return CURRENT_TENANT.get();
    }

    /**
     * Check if tenant context is set
     */
    public static boolean isSet() {
        return CURRENT_TENANT.get() != null;
    }

    /**
     * Clear the tenant context (must be called after request completion)
     */
    public static void clear() {
        UUID tenantId = CURRENT_TENANT.get();
        if (tenantId != null) {
            log.debug("Clearing tenant context: {}", tenantId);
        }
        CURRENT_TENANT.remove();
    }

    /**
     * Execute a block with a specific tenant context
     */
    public static <T> T executeInTenantContext(UUID tenantId, TenantAction<T> action) {
        UUID previousTenant = getTenantIdOrNull();
        try {
            setTenantId(tenantId);
            return action.execute();
        } finally {
            clear();
            if (previousTenant != null) {
                setTenantId(previousTenant);
            }
        }
    }

    @FunctionalInterface
    public interface TenantAction<T> {
        T execute();
    }
}

