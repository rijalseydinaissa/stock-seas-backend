package com.example.stock_saas.shared.common.constant;


import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Default permissions for each role.
 * This defines the baseline permissions that each role receives.
 * Additional permissions can be granted on a per-user basis.
 */
public class RolePermissions {

    private static final Map<Role, Set<Permission>> ROLE_PERMISSIONS = Map.of(

            // SUPER_ADMIN: All permissions
            Role.SUPER_ADMIN, EnumSet.allOf(Permission.class),

            // TENANT_OWNER: All tenant-level permissions
            Role.TENANT_OWNER, EnumSet.of(
                    // Auth
                    Permission.AUTH_LOGIN, Permission.AUTH_LOGOUT, Permission.AUTH_REFRESH,
                    Permission.AUTH_MFA_CONFIGURE, Permission.AUTH_MFA_VERIFY,
                    Permission.AUTH_SESSION_MANAGE,
                    // Tenant
                    Permission.TENANT_VIEW, Permission.TENANT_UPDATE,
                    Permission.TENANT_MANAGE_SUBSCRIPTION,
                    // User (full)
                    Permission.USER_CREATE, Permission.USER_VIEW, Permission.USER_UPDATE,
                    Permission.USER_DELETE, Permission.USER_ASSIGN_ROLE,
                    Permission.USER_MANAGE_PERMISSIONS,
                    // Product (full)
                    Permission.PRODUCT_CREATE, Permission.PRODUCT_VIEW, Permission.PRODUCT_UPDATE,
                    Permission.PRODUCT_DELETE, Permission.PRODUCT_MANAGE_CATEGORIES,
                    Permission.PRODUCT_MANAGE_VARIANTS,
                    // Warehouse (full)
                    Permission.WAREHOUSE_CREATE, Permission.WAREHOUSE_VIEW,
                    Permission.WAREHOUSE_UPDATE, Permission.WAREHOUSE_DELETE,
                    Permission.WAREHOUSE_MANAGE_LOCATIONS,
                    // Inventory (full)
                    Permission.INVENTORY_VIEW, Permission.INVENTORY_ADJUST,
                    Permission.INVENTORY_TRANSFER, Permission.INVENTORY_SNAPSHOT,
                    Permission.INVENTORY_RECONCILE,
                    // Purchase (full)
                    Permission.PURCHASE_CREATE, Permission.PURCHASE_VIEW, Permission.PURCHASE_UPDATE,
                    Permission.PURCHASE_DELETE, Permission.PURCHASE_APPROVE,
                    Permission.PURCHASE_RECEIVE, Permission.PURCHASE_CANCEL,
                    // Sales (full)
                    Permission.SALES_CREATE, Permission.SALES_VIEW, Permission.SALES_UPDATE,
                    Permission.SALES_DELETE, Permission.SALES_APPROVE,
                    Permission.SALES_SHIP, Permission.SALES_CANCEL,
                    // Billing (full)
                    Permission.BILLING_VIEW, Permission.BILLING_MANAGE_SUBSCRIPTION,
                    Permission.BILLING_VIEW_INVOICES, Permission.BILLING_DOWNLOAD_INVOICE,
                    // Reporting (full)
                    Permission.REPORT_VIEW_BASIC, Permission.REPORT_VIEW_FINANCIAL,
                    Permission.REPORT_VIEW_ANALYTICS, Permission.REPORT_EXPORT
            ),

            // ADMIN: Management permissions (no tenant/subscription management)
            Role.ADMIN, EnumSet.of(
                    // Auth
                    Permission.AUTH_LOGIN, Permission.AUTH_LOGOUT, Permission.AUTH_REFRESH,
                    Permission.AUTH_MFA_CONFIGURE, Permission.AUTH_MFA_VERIFY,
                    // Tenant (view only)
                    Permission.TENANT_VIEW,
                    // User (manage)
                    Permission.USER_CREATE, Permission.USER_VIEW, Permission.USER_UPDATE,
                    Permission.USER_DELETE,
                    // Product (full)
                    Permission.PRODUCT_CREATE, Permission.PRODUCT_VIEW, Permission.PRODUCT_UPDATE,
                    Permission.PRODUCT_DELETE, Permission.PRODUCT_MANAGE_CATEGORIES,
                    Permission.PRODUCT_MANAGE_VARIANTS,
                    // Warehouse (manage)
                    Permission.WAREHOUSE_CREATE, Permission.WAREHOUSE_VIEW,
                    Permission.WAREHOUSE_UPDATE, Permission.WAREHOUSE_MANAGE_LOCATIONS,
                    // Inventory (full)
                    Permission.INVENTORY_VIEW, Permission.INVENTORY_ADJUST,
                    Permission.INVENTORY_TRANSFER, Permission.INVENTORY_SNAPSHOT,
                    Permission.INVENTORY_RECONCILE,
                    // Purchase (full)
                    Permission.PURCHASE_CREATE, Permission.PURCHASE_VIEW, Permission.PURCHASE_UPDATE,
                    Permission.PURCHASE_APPROVE, Permission.PURCHASE_RECEIVE,
                    // Sales (full)
                    Permission.SALES_CREATE, Permission.SALES_VIEW, Permission.SALES_UPDATE,
                    Permission.SALES_APPROVE, Permission.SALES_SHIP, Permission.SALES_CANCEL,
                    // Billing (view)
                    Permission.BILLING_VIEW, Permission.BILLING_VIEW_INVOICES,
                    Permission.BILLING_DOWNLOAD_INVOICE,
                    // Reporting (full)
                    Permission.REPORT_VIEW_BASIC, Permission.REPORT_VIEW_FINANCIAL,
                    Permission.REPORT_VIEW_ANALYTICS, Permission.REPORT_EXPORT
            ),

            // MANAGER: Operational management
            Role.MANAGER, EnumSet.of(
                    // Auth
                    Permission.AUTH_LOGIN, Permission.AUTH_LOGOUT, Permission.AUTH_REFRESH,
                    Permission.AUTH_MFA_CONFIGURE, Permission.AUTH_MFA_VERIFY,
                    // User (view)
                    Permission.USER_VIEW,
                    // Product (manage)
                    Permission.PRODUCT_CREATE, Permission.PRODUCT_VIEW, Permission.PRODUCT_UPDATE,
                    Permission.PRODUCT_MANAGE_CATEGORIES, Permission.PRODUCT_MANAGE_VARIANTS,
                    // Warehouse (view and update)
                    Permission.WAREHOUSE_VIEW, Permission.WAREHOUSE_UPDATE,
                    Permission.WAREHOUSE_MANAGE_LOCATIONS,
                    // Inventory (manage)
                    Permission.INVENTORY_VIEW, Permission.INVENTORY_ADJUST,
                    Permission.INVENTORY_TRANSFER,
                    // Purchase (approve)
                    Permission.PURCHASE_CREATE, Permission.PURCHASE_VIEW, Permission.PURCHASE_UPDATE,
                    Permission.PURCHASE_APPROVE, Permission.PURCHASE_RECEIVE,
                    // Sales (approve)
                    Permission.SALES_CREATE, Permission.SALES_VIEW, Permission.SALES_UPDATE,
                    Permission.SALES_APPROVE, Permission.SALES_SHIP, Permission.SALES_CANCEL,
                    // Reporting (financial)
                    Permission.REPORT_VIEW_BASIC, Permission.REPORT_VIEW_FINANCIAL,
                    Permission.REPORT_EXPORT
            ),

            // EMPLOYEE: Basic operations
            Role.EMPLOYEE, EnumSet.of(
                    // Auth
                    Permission.AUTH_LOGIN, Permission.AUTH_LOGOUT, Permission.AUTH_REFRESH,
                    Permission.AUTH_MFA_CONFIGURE, Permission.AUTH_MFA_VERIFY,
                    // Product (view and create)
                    Permission.PRODUCT_VIEW, Permission.PRODUCT_CREATE, Permission.PRODUCT_UPDATE,
                    // Warehouse (view)
                    Permission.WAREHOUSE_VIEW,
                    // Inventory (view)
                    Permission.INVENTORY_VIEW,
                    // Purchase (create and receive)
                    Permission.PURCHASE_CREATE, Permission.PURCHASE_VIEW,
                    Permission.PURCHASE_RECEIVE,
                    // Sales (create and ship)
                    Permission.SALES_CREATE, Permission.SALES_VIEW, Permission.SALES_SHIP,
                    // Reporting (basic)
                    Permission.REPORT_VIEW_BASIC
            ),

            // VIEWER: Read-only access
            Role.VIEWER, EnumSet.of(
                    // Auth
                    Permission.AUTH_LOGIN, Permission.AUTH_LOGOUT, Permission.AUTH_REFRESH,
                    // Product (view)
                    Permission.PRODUCT_VIEW,
                    // Warehouse (view)
                    Permission.WAREHOUSE_VIEW,
                    // Inventory (view)
                    Permission.INVENTORY_VIEW,
                    // Purchase (view)
                    Permission.PURCHASE_VIEW,
                    // Sales (view)
                    Permission.SALES_VIEW,
                    // Reporting (basic)
                    Permission.REPORT_VIEW_BASIC
            )
    );

    private RolePermissions() {
        // Utility class
    }

    /**
     * Get default permissions for a role
     */
    public static Set<Permission> getPermissionsForRole(Role role) {
        return ROLE_PERMISSIONS.getOrDefault(role, EnumSet.noneOf(Permission.class));
    }

    /**
     * Check if a role has a specific permission by default
     */
    public static boolean roleHasPermission(Role role, Permission permission) {
        return getPermissionsForRole(role).contains(permission);
    }

    /**
     * Get all permissions from multiple roles (union)
     */
    public static Set<Permission> getPermissionsForRoles(Set<Role> roles) {
        Set<Permission> allPermissions = EnumSet.noneOf(Permission.class);
        for (Role role : roles) {
            allPermissions.addAll(getPermissionsForRole(role));
        }
        return allPermissions;
    }
}

