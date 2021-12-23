package com.musalasoft.drone.service;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.entity.LoadDrone;
import com.musalasoft.drone.entity.ResponseData;





@Repository
public interface LoadDroneservice extends CrudRepository <LoadDrone, Integer> {
	
	/*
	 * 
	 * */
	
	@Modifying
	@Query("update LoadDrone a set a.image =:image where a.droneId=:id")
	public String updateLoadedDronewithImage (@Param ("id") int id,@Param ("image") String image);
	
	
	

}
