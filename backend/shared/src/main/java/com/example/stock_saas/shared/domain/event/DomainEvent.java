package com.example.stock_saas.shared.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Marker interface for all domain events.
 * Domain events represent something that happened in the domain that domain experts care about.
 *
 * Events are:
 * - Immutable (always in the past)
 * - Published after successful persistence
 * - Named in past tense (e.g., ProductCreated, OrderShipped)
 */
public interface DomainEvent {

    /**
     * Unique identifier for this event occurrence
     */
    UUID getEventId();

    /**
     * When the event occurred
     */
    Instant getOccurredAt();

    /**
     * Tenant context for the event
     */
    UUID getTenantId();

    /**
     * User who triggered the event (if applicable)
     */
    UUID getTriggeredBy();

    /**
     * Event type identifier (class simple name by default)
     */
    default String getEventType() {
        return this.getClass().getSimpleName();
    }
}

