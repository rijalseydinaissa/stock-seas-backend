package com.example.stock_saas.shared.infrastructure.security;

import com.example.stock_saas.shared.common.constant.Permission;
import com.example.stock_saas.shared.common.constant.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Security utility methods for accessing current authentication context.
 */
public class SecurityUtils {

    private SecurityUtils() {
        // Utility class
    }

    /**
     * Get the current authentication object
     */
    public static Optional<Authentication> getCurrentAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !"anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.of(authentication);
        }
        return Optional.empty();
    }

    /**
     * Get the current user's ID from JWT claims
     */
    public static Optional<UUID> getCurrentUserId() {
        return getCurrentAuthentication()
                .map(auth -> {
                    if (auth.getPrincipal() instanceof UserPrincipal principal) {
                        return principal.getId();
                    }
                    return null;
                });
    }

    /**
     * Get the current user's tenant ID from JWT claims
     */
    public static Optional<UUID> getCurrentTenantId() {
        return getCurrentAuthentication()
                .map(auth -> {
                    if (auth.getPrincipal() instanceof UserPrincipal principal) {
                        return principal.getTenantId();
                    }
                    return null;
                });
    }

    /**
     * Get the current user's username
     */
    public static Optional<String> getCurrentUsername() {
        return getCurrentAuthentication()
                .map(Authentication::getName);
    }

    /**
     * Get the current user's roles
     */
    public static Set<Role> getCurrentUserRoles() {
        return getCurrentAuthentication()
                .map(auth -> {
                    if (auth.getPrincipal() instanceof UserPrincipal principal) {
                        return principal.getRoles();
                    }
                    return Set.<Role>of();
                })
                .orElse(Set.of());
    }

    /**
     * Get the current user's permissions
     */
    public static Set<Permission> getCurrentUserPermissions() {
        return getCurrentAuthentication()
                .map(auth -> {
                    if (auth.getPrincipal() instanceof UserPrincipal principal) {
                        return principal.getPermissions();
                    }
                    return Set.<Permission>of();
                })
                .orElse(Set.of());
    }

    /**
     * Check if current user has a specific role
     */
    public static boolean hasRole(Role role) {
        return getCurrentUserRoles().contains(role);
    }

    /**
     * Check if current user has any of the specified roles
     */
    public static boolean hasAnyRole(Role... roles) {
        Set<Role> userRoles = getCurrentUserRoles();
        for (Role role : roles) {
            if (userRoles.contains(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if current user has a specific permission
     */
    public static boolean hasPermission(Permission permission) {
        return getCurrentUserPermissions().contains(permission);
    }

    /**
     * Check if current user has all specified permissions
     */
    public static boolean hasAllPermissions(Permission... permissions) {
        Set<Permission> userPermissions = getCurrentUserPermissions();
        for (Permission permission : permissions) {
            if (!userPermissions.contains(permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if current user has any of the specified permissions
     */
    public static boolean hasAnyPermission(Permission... permissions) {
        Set<Permission> userPermissions = getCurrentUserPermissions();
        for (Permission permission : permissions) {
            if (userPermissions.contains(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if user is authenticated
     */
    public static boolean isAuthenticated() {
        return getCurrentAuthentication().isPresent();
    }

    /**
     * Get Spring Security authorities as strings
     */
    public static Set<String> getCurrentAuthorities() {
        return getCurrentAuthentication()
                .map(Authentication::getAuthorities)
                .stream()
                .flatMap(Collection::stream)
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}
