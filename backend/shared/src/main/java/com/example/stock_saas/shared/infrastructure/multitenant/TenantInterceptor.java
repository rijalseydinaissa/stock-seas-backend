package com.example.stock_saas.shared.infrastructure.multitenant;

import com.example.stock_saas.shared.domain.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Hibernate interceptor that automatically sets tenant_id on entity save.
 * This ensures ALL entities have a tenant_id without manual intervention.
 */
@Slf4j
@Component
public class TenantInterceptor implements Interceptor {

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {

        if (entity instanceof BaseEntity baseEntity) {
            UUID tenantId = TenantContext.getTenantId();

            // Set tenant ID if not already set (for new entities)
            if (baseEntity.getTenantId() == null) {
                baseEntity.setTenantId(tenantId);

                // Update state array for Hibernate
                for (int i = 0; i < propertyNames.length; i++) {
                    if ("tenantId".equals(propertyNames[i])) {
                        state[i] = tenantId;
                        log.debug("Auto-set tenant_id={} for {}", tenantId, entity.getClass().getSimpleName());
                        return true;
                    }
                }
            } else {
                // Validate tenant ID matches context (security check)
                if (!baseEntity.getTenantId().equals(tenantId)) {
                    throw new CallbackException(
                            String.format("Tenant mismatch: entity has %s but context has %s",
                                    baseEntity.getTenantId(), tenantId)
                    );
                }
            }
        }

        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState,
                                Object[] previousState, String[] propertyNames, Type[] types)
            throws CallbackException {

        if (entity instanceof BaseEntity baseEntity) {
            UUID tenantId = TenantContext.getTenantId();

            // Always validate tenant ID on updates
            if (!baseEntity.getTenantId().equals(tenantId)) {
                throw new CallbackException(
                        String.format("Cannot update entity from different tenant: entity=%s, context=%s",
                                baseEntity.getTenantId(), tenantId)
                );
            }
        }

        return false;
    }

    @Override
    public void onDelete(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {

        if (entity instanceof BaseEntity baseEntity) {
            UUID tenantId = TenantContext.getTenantId();

            // Validate tenant ID on deletes
            if (!baseEntity.getTenantId().equals(tenantId)) {
                throw new CallbackException(
                        String.format("Cannot delete entity from different tenant: entity=%s, context=%s",
                                baseEntity.getTenantId(), tenantId)
                );
            }
        }
    }
}
