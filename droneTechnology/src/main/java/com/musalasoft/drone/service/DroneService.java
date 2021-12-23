package com.musalasoft.drone.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.musalasoft.drone.entity.BatteryCapacity;
import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.entity.ResponseData;

@Repository
public interface DroneService extends JpaRepository<Drone, Integer> {

	// public void checkDroneBatteryByDroneId(@Valid String id);

	
	/*
	 * 
	 * This method gets the battery level of drone
	 * using the id(serial number)
	 * 
	 * 
	 * */
	
	@Query(value = "SELECT a.batteryCapacity FROM Drone a where a.serialNumber= :serialNumber")
	public double checkDroneBatteryByDroneId(int serialNumber);
	
	
	
	@Query(value = "SELECT a.batteryCapacity,a.serialNumber FROM Drone a where a.batteryCapacity < 25")
	Collection<BatteryCapacity> checkDroneBatteryJob();
	
	
	
	
	
	/*
	 * finds all drones that are available for loading
	 * 
	 * 
	 * */
	
	
	
	@Query(value = "SELECT * FROM drone u WHERE u.state = 'IDLE'", nativeQuery = true)
	Collection<Drone> findAllActiveDronesNative();

	

}
