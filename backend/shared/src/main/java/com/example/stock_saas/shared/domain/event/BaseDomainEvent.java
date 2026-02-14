package com.example.stock_saas.shared.domain.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

/**
 * Base implementation for domain events with common fields.
 */
@Getter
public abstract class BaseDomainEvent implements DomainEvent {

    private final UUID eventId;
    private final Instant occurredAt;
    private final UUID tenantId;
    private final UUID triggeredBy;

    protected BaseDomainEvent(UUID tenantId, UUID triggeredBy) {
        this.eventId = UUID.randomUUID();
        this.occurredAt = Instant.now();
        this.tenantId = tenantId;
        this.triggeredBy = triggeredBy;
    }

    @Override
    public String toString() {
        return String.format("%s[eventId=%s, tenantId=%s, occurredAt=%s]",
                getEventType(), eventId, tenantId, occurredAt);
    }
}

