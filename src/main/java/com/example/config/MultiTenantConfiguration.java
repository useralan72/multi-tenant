package com.example.config;

import com.example.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class MultiTenantConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public TenantContext tenantContext() {
        return new TenantContext();
    }

    /**
     * Defines the data source for the application
     * @return
     */
    @Bean
    public DataSource dataSource() {
        Map<Object,Object> resolvedDataSources = new HashMap<>();
        String driverName = env.getProperty("spring.multitenancy.datasource.driver-class-name");
        String[] tenantIds = env.getProperty("spring.multitenancy.datasource.tenantid", String[].class);
        String[] dataSourceurls = env.getProperty("spring.multitenancy.datasource.url", String[].class);
        String[] dataSourceUsers = env.getProperty("spring.multitenancy.datasource.username", String[].class);
        String[] dataSourceUsersPasswords = env.getProperty("spring.multitenancy.datasource.password", String[].class);

        AtomicInteger counter = new AtomicInteger(0);
        Arrays.stream(dataSourceurls).forEach(s -> {
            DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader());
            dataSourceBuilder.driverClassName(driverName)
                    .url(s)
                    .username(dataSourceUsers[counter.get()])
                    .password(dataSourceUsersPasswords[counter.get()]);
            resolvedDataSources.put(tenantIds[counter.getAndIncrement()], dataSourceBuilder.build());
        });

        //set for use later
        tenantContext().setResolvedDataSources(resolvedDataSources);

        // Create the final multi-tenant source.
        MultiTenantDataSource dataSource = new MultiTenantDataSource();
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get(tenantIds[0]));
        dataSource.setTargetDataSources(resolvedDataSources);

        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet();

        return dataSource;
    }
}
