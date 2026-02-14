package com.example.stock_saas.shared.domain.exception;

import java.util.UUID;

/**
 * Exception for tenant isolation violations.
 */
public class TenantMismatchException extends DomainException {

    public TenantMismatchException(UUID expectedTenantId, UUID actualTenantId) {
        super(
                String.format("Tenant mismatch: expected %s but got %s",
                        expectedTenantId, actualTenantId),
                "TENANT_MISMATCH"
        );
    }

    public TenantMismatchException(String message) {
        super(message, "TENANT_MISMATCH");
    }
}
