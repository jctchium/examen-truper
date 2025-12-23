package com.chiu.projects.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.Observation.Event;
import io.micrometer.observation.ObservationHandler;

public class PerformanceTrackerHandler implements ObservationHandler<Observation.Context> {
	Logger logger = LoggerFactory.getLogger(PerformanceTrackerHandler.class);
	
	@Override
	public void onStart(Context context) {
		logger.info("Execution Started {}", context.getName());
		// TODO Auto-generated method stub
		context.put("time", System.currentTimeMillis());
	}

	@Override
	public void onError(Context context) {
		logger.error("Error ocurred {]", context.getError().getMessage());
	}

	@Override
	public void onEvent(Event event, Context context) {
		// TODO Auto-generated method stub
		ObservationHandler.super.onEvent(event, context);
	}

	@Override
	public void onScopeOpened(Context context) {
		// TODO Auto-generated method stub
		ObservationHandler.super.onScopeOpened(context);
	}

	@Override
	public void onScopeClosed(Context context) {
		// TODO Auto-generated method stub
		ObservationHandler.super.onScopeClosed(context);
	}

	@Override
	public void onScopeReset(Context context) {
		// TODO Auto-generated method stub
		ObservationHandler.super.onScopeReset(context);
	}

	@Override
	public void onStop(Context context) {
		logger.info("execution Stopped " + context.getName() + " duration " + 
				(System.currentTimeMillis() - context.getOrDefault("time", 0L)));
	}

	@Override
	public boolean supportsContext(Context context) {
		// TODO Auto-generated method stub
		return true;
	}
}
