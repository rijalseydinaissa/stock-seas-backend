package com.example.stock_saas.shared.infrastructure.event;

import com.example.stock_saas.shared.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Publisher for domain events using Spring's event mechanism.
 * Events are published AFTER successful transaction commit.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DomainEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    /**
     * Publish a domain event.
     * The event will be propagated to all registered listeners.
     *
     * @param event The domain event to publish
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void publish(DomainEvent event) {
        log.info("Publishing domain event: {}", event.getEventType());
        log.debug("Event details: {}", event);

        eventPublisher.publishEvent(event);
    }

    /**
     * Publish multiple domain events in sequence
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void publishAll(DomainEvent... events) {
        for (DomainEvent event : events) {
            publish(event);
        }
    }
}

