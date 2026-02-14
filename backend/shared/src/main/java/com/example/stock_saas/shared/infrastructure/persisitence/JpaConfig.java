package com.example.stock_saas.shared.infrastructure.persisitence;

import com.example.stock_saas.shared.infrastructure.multitenant.TenantInterceptor;
import com.example.stock_saas.shared.infrastructure.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

/**
 * JPA configuration with auditing support.
 * Automatically tracks who created/modified entities.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class JpaConfig {

    private final TenantInterceptor tenantInterceptor;

    /**
     * Provides the current auditor (user ID) from security context
     */
    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> SecurityUtils.getCurrentUserId();
    }

    /**
     * Register Hibernate interceptor for tenant isolation
     */
    @Bean
    public org.hibernate.Interceptor hibernateInterceptor() {
        return tenantInterceptor;
    }
}

