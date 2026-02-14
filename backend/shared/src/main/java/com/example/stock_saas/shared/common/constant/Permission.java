package com.example.stock_saas.shared.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Permission {

    // ==================== AUTH MODULE ====================
    AUTH_LOGIN("auth:login", "Login to the system"),
    AUTH_LOGOUT("auth:logout", "Logout from the system"),
    AUTH_REFRESH("auth:refresh", "Refresh authentication token"),
    AUTH_MFA_CONFIGURE("auth:mfa:configure", "Configure MFA"),
    AUTH_MFA_VERIFY("auth:mfa:verify", "Verify MFA code"),
    AUTH_SESSION_MANAGE("auth:session:manage", "Manage user sessions"),

    // ==================== TENANT MODULE ====================
    TENANT_CREATE("tenant:create", "Create new tenant"),
    TENANT_VIEW("tenant:view", "View tenant information"),
    TENANT_UPDATE("tenant:update", "Update tenant settings"),
    TENANT_DELETE("tenant:delete", "Delete tenant"),
    TENANT_MANAGE_SUBSCRIPTION("tenant:subscription:manage", "Manage tenant subscription"),

    // ==================== USER MODULE ====================
    USER_CREATE("user:create", "Create new user"),
    USER_VIEW("user:view", "View user information"),
    USER_UPDATE("user:update", "Update user information"),
    USER_DELETE("user:delete", "Delete user"),
    USER_ASSIGN_ROLE("user:role:assign", "Assign roles to users"),
    USER_MANAGE_PERMISSIONS("user:permissions:manage", "Manage user permissions"),

    // ==================== PRODUCT MODULE ====================
    PRODUCT_CREATE("product:create", "Create new product"),
    PRODUCT_VIEW("product:view", "View product information"),
    PRODUCT_UPDATE("product:update", "Update product information"),
    PRODUCT_DELETE("product:delete", "Delete product"),
    PRODUCT_MANAGE_CATEGORIES("product:category:manage", "Manage product categories"),
    PRODUCT_MANAGE_VARIANTS("product:variant:manage", "Manage product variants"),

    // ==================== WAREHOUSE MODULE ====================
    WAREHOUSE_CREATE("warehouse:create", "Create new warehouse"),
    WAREHOUSE_VIEW("warehouse:view", "View warehouse information"),
    WAREHOUSE_UPDATE("warehouse:update", "Update warehouse information"),
    WAREHOUSE_DELETE("warehouse:delete", "Delete warehouse"),
    WAREHOUSE_MANAGE_LOCATIONS("warehouse:location:manage", "Manage warehouse locations"),

    // ==================== INVENTORY MODULE ====================
    INVENTORY_VIEW("inventory:view", "View inventory levels"),
    INVENTORY_ADJUST("inventory:adjust", "Adjust inventory levels"),
    INVENTORY_TRANSFER("inventory:transfer", "Transfer inventory between locations"),
    INVENTORY_SNAPSHOT("inventory:snapshot", "Create inventory snapshot"),
    INVENTORY_RECONCILE("inventory:reconcile", "Reconcile inventory"),

    // ==================== PURCHASE MODULE ====================
    PURCHASE_CREATE("purchase:create", "Create purchase order"),
    PURCHASE_VIEW("purchase:view", "View purchase orders"),
    PURCHASE_UPDATE("purchase:update", "Update purchase order"),
    PURCHASE_DELETE("purchase:delete", "Delete purchase order"),
    PURCHASE_APPROVE("purchase:approve", "Approve purchase order"),
    PURCHASE_RECEIVE("purchase:receive", "Receive purchased goods"),
    PURCHASE_CANCEL("purchase:cancel", "Cancel purchase order"),

    // ==================== SALES MODULE ====================
    SALES_CREATE("sales:create", "Create sales order"),
    SALES_VIEW("sales:view", "View sales orders"),
    SALES_UPDATE("sales:update", "Update sales order"),
    SALES_DELETE("sales:delete", "Delete sales order"),
    SALES_APPROVE("sales:approve", "Approve sales order"),
    SALES_SHIP("sales:ship", "Ship sales order"),
    SALES_CANCEL("sales:cancel", "Cancel sales order"),

    // ==================== BILLING MODULE ====================
    BILLING_VIEW("billing:view", "View billing information"),
    BILLING_MANAGE_SUBSCRIPTION("billing:subscription:manage", "Manage subscription"),
    BILLING_VIEW_INVOICES("billing:invoice:view", "View invoices"),
    BILLING_DOWNLOAD_INVOICE("billing:invoice:download", "Download invoice"),

    // ==================== REPORTING MODULE ====================
    REPORT_VIEW_BASIC("report:view:basic", "View basic reports"),
    REPORT_VIEW_FINANCIAL("report:view:financial", "View financial reports"),
    REPORT_VIEW_ANALYTICS("report:view:analytics", "View advanced analytics"),
    REPORT_EXPORT("report:export", "Export reports"),

    // ==================== SYSTEM (SUPER ADMIN ONLY) ====================
    SYSTEM_MANAGE("system:manage", "Manage system configuration"),
    SYSTEM_VIEW_LOGS("system:logs:view", "View system logs"),
    SYSTEM_VIEW_METRICS("system:metrics:view", "View system metrics");

    private final String code;
    private final String description;

    @Override
    public String toString() {
        return code;
    }

    public static Permission fromCode(String code) {
        for (Permission permission : values()) {
            if (permission.code.equals(code)) {
                return permission;
            }
        }
        throw new IllegalArgumentException("Unknown permission code: " + code);
    }
}

