package com.example.config;

import com.example.TenantContext;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

/**
 * Created by AE2 on 29/06/2016.
 */
@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Autowired
    protected TenantContext tenantContext;

    @Override
    protected DataSource selectAnyDataSource() {
        //get the first value in the map
        Optional<Object> firstValue = tenantContext.getResolvedDataSources().entrySet().stream()
                .map(Map.Entry::getValue).findFirst();
        return (DataSource) firstValue.get();
    }

    @Override
    protected DataSource selectDataSource(String s) {
        return (DataSource) tenantContext.getResolvedDataSources().get(s);
    }
}
