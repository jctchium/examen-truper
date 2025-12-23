package com.chiu.projects.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;

import com.chiu.projects.aspect.PerformanceTrackerHandler;

@Configuration
public class ObservationAspectConfig {
	@Bean
	public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
		observationRegistry.observationConfig().observationHandler(new PerformanceTrackerHandler());
		return new ObservedAspect(observationRegistry);
	}
}