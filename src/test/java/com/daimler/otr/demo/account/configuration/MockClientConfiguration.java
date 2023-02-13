package com.daimler.otr.demo.account.configuration;

import com.daimler.otr.demo.account.client.PartsManagementClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class MockClientConfiguration {

    @Bean
    public PartsManagementClient mockMonitoringManagementClient() {
        return mock(PartsManagementClient.class);
    }
}
