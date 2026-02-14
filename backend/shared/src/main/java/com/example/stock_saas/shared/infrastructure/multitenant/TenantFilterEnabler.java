package com.example.stock_saas.shared.infrastructure.multitenant;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Utility to enable Hibernate tenant filter on all queries.
 * This ensures automatic filtering by tenant_id at the database level.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TenantFilterEnabler {

    private final EntityManager entityManager;

    /**
     * Enable the tenant filter for the current session
     */
    public void enableTenantFilter() {
        if (!TenantContext.isSet()) {
            log.warn("Tenant filter requested but no tenant context set");
            return;
        }

        UUID tenantId = TenantContext.getTenantId();
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("tenantFilter");
        filter.setParameter("tenantId", tenantId);

        log.debug("Enabled tenant filter for tenant: {}", tenantId);
    }

    /**
     * Disable the tenant filter (use with extreme caution!)
     */
    public void disableTenantFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.disableFilter("tenantFilter");
        log.warn("Tenant filter disabled - this should only be used in specific admin operations");
    }

    /**
     * Check if tenant filter is currently enabled
     */
    public boolean isTenantFilterEnabled() {
        Session session = entityManager.unwrap(Session.class);
        return session.getEnabledFilter("tenantFilter") != null;
    }
}

