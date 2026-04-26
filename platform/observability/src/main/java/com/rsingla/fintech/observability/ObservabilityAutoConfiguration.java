package com.rsingla.fintech.observability;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ObservabilityAutoConfiguration {
    @Bean
    MeterFilter applicationTag(@Value("${spring.application.name:fintech-service}") String applicationName) {
        return MeterFilter.commonTags(java.util.List.of(Tag.of("application", applicationName)));
    }
}
