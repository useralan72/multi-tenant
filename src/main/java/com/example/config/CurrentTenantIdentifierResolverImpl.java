package com.example.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by AE2 on 29/06/2016.
 */
@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    @Value("${spring.multitenancy.datasource.tenantid}")
    String[] tenantIds;

    private static final String TENANT_ID = "X-TenantID";

    @Override
    public String resolveCurrentTenantIdentifier() {
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String tenantId = request.getHeader(TENANT_ID);
            if (tenantId != null) {
                return tenantId;
            }
        }
        return tenantIds[0];
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
