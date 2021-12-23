package com.musalasoft.drone.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.musalasoft.drone.entity.BatteryCapacity;

/*
 * 
 * - a job that Prevent the drone from being in LOADING state if the battery level is **below 25%**;
 *  checks the battery of */

@Service
public class BatteryJob {
	
	@Autowired
	DroneService droneService;
	
	
	
	private static Logger logger = LoggerFactory.getLogger(BatteryJob.class);
	private final int ELAPSED_TIME_IN_MINUTES = 20;
	
	 @Scheduled(fixedDelayString = "${scheduler.receive.fixed.delay:15000}", initialDelayString = "${scheduler.receive.initial.delay:15000}")
		public void checkBatteryLevelTask() {
	    	try {
	    	logger.info("RUNNING BACKGROUND JOB FOR  BATTERY JOB  ");
	    	Collection<BatteryCapacity> bat= (Collection<BatteryCapacity>) new BatteryCapacity();
			
	    	droneService.checkDroneBatteryJob();
		
	    	}catch(Exception ex){
	    		logger.info("{}"+ex);
	    		ex.printStackTrace();
	    		
	    	}
		}

}
