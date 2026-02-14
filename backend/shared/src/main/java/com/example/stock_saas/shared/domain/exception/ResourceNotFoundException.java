package com.example.stock_saas.shared.domain.exception;

import java.util.UUID;

/**
 * Exception when a requested resource is not found.
 */
public class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException(String resourceType, UUID id) {
        super(
                String.format("%s with id '%s' not found", resourceType, id),
                "RESOURCE_NOT_FOUND"
        );
    }

    public ResourceNotFoundException(String resourceType, String identifier) {
        super(
                String.format("%s with identifier '%s' not found", resourceType, identifier),
                "RESOURCE_NOT_FOUND"
        );
    }
}