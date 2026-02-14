package com.example.stock_saas.shared.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Application roles hierarchy.
 * Roles are hierarchical: higher roles include all permissions of lower roles.
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    /**
     * Platform super administrator (Anthropic level)
     * Can manage all tenants and platform configuration
     */
    SUPER_ADMIN(100, "Super Administrator"),

    /**
     * Tenant owner (full control over their tenant)
     * Can manage subscription, users, and all resources
     */
    TENANT_OWNER(90, "Tenant Owner"),

    /**
     * Administrator (management level)
     * Can manage users and most resources within tenant
     */
    ADMIN(80, "Administrator"),

    /**
     * Manager (operational level)
     * Can approve transactions and manage day-to-day operations
     */
    MANAGER(70, "Manager"),

    /**
     * Employee (basic operational level)
     * Can create and manage basic transactions
     */
    EMPLOYEE(60, "Employee"),

    /**
     * Viewer (read-only access)
     * Can only view data, no modifications
     */
    VIEWER(50, "Viewer");

    private final int level;
    private final String displayName;

    /**
     * Check if this role has at least the specified level
     */
    public boolean hasLevel(Role role) {
        return this.level >= role.level;
    }

    /**
     * Check if this role is higher than the specified role
     */
    public boolean isHigherThan(Role role) {
        return this.level > role.level;
    }

    /**
     * Get all roles at or below this level
     */
    public Set<Role> getRolesAtOrBelow() {
        return Arrays.stream(values())
                .filter(r -> r.level <= this.level)
                .collect(Collectors.toSet());
    }

    /**
     * Get all roles above this level
     */
    public Set<Role> getRolesAbove() {
        return Arrays.stream(values())
                .filter(r -> r.level > this.level)
                .collect(Collectors.toSet());
    }

    public static Role fromString(String role) {
        return valueOf(role.toUpperCase());
    }
}

