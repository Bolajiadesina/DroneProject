package com.musalasoft.drone.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Clob;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.engine.jdbc.WrappedBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.musalasoft.drone.entity.CreateDrone;
import com.musalasoft.drone.entity.Drone;
import com.musalasoft.drone.entity.LoadDrone;
import com.musalasoft.drone.entity.OnlyForImagesFileWrapper;
import com.musalasoft.drone.entity.ResponseData;
import com.musalasoft.drone.entity.SaveMedicationImage;
import com.musalasoft.drone.service.DroneService;
import com.musalasoft.drone.service.LoadDroneservice;


/**
 * The Rest Api controller class for as the entry for all
 * urls
 * 
 * 
 * */
		




@RestController
@RequestMapping("/drone")
public class DispatchController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DispatchController.class);

	@Autowired
	DroneService droneService;

	@Autowired
	LoadDroneservice loadDroneservice;
	



	/*
	 *  registering a  new drone with
	 *  a unique id. it could be a single drone or a 
	 *  list of drones to be registered at once
	 *
	*/
	
	@RequestMapping(value = { "/registerDrone" }, method = { RequestMethod.POST }, produces = {
			"application/json" }, consumes = { "application/json" })

	public ResponseEntity<ResponseData> registerDrone(@RequestBody CreateDrone request) {
		logger.info("Request Data==>>>>>>>" + request);

		ResponseData resp = new ResponseData();

		try {

			List<Drone> newDrone = request.getDrone();
			for (Drone entity : newDrone) {
				droneService.save(entity);

			}

			resp.setResponseCode("000");
			resp.setResponseMessage("SUCCESS");
			logger.info("Response Data==>>>>>>>" + resp);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<ResponseData>(resp, HttpStatus.OK);
	}


/* loading a drone with medication items
 * Also contains informations like name, code ,weight
 * 
 */
	@PostMapping(value = "/loadDroneWithMedication" ,		
			produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE} )

	public ResponseEntity<ResponseData> loadDroneWithMedication( @RequestParam("file") MultipartFile file,
			@RequestParam ("droneId") int droneId,
			@RequestParam  ("name")  String name,
			@RequestParam  ("code")  String code , 
			@RequestParam ("weight") double weight) throws Throwable {

		LoadDrone img= new LoadDrone();
		ResponseData resp = new ResponseData();
		  String imageString="";
			try {
			  logger.info("getting here");;
		    if (file != null && !file.getOriginalFilename().isEmpty()) {
		        byte[] data = file.getBytes();
		         imageString = Base64.getEncoder().encodeToString(data);
		      
		         
		         logger.info("imageString"+imageString);
		         
		         imageString= imageString.substring(0, 253) ;
		       
		         
		         img.setImage( imageString);
		         img.setDroneId(droneId);
		         img.setName(name.toUpperCase());
		         img.setWeight(weight);
		         img.setCode(code.toUpperCase());
		         
		         loadDroneservice.save(img);

					resp.setResponseCode("000");
					resp.setResponseMessage("SUCCESS");
					logger.info("Response Data==>>>>>>>" + resp);
		    }
		
		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<ResponseData>(resp, HttpStatus.OK);
	}

	
	
	/*
	 * checking loaded medication items for a given drone; 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = { "/checkLoadedMedicationByDroneId/{id}" }, method = { RequestMethod.GET }, produces = {
			"application/json" })

	public ResponseEntity<ResponseData> checkLoadedMedicationByDroneId(@Valid @PathVariable("id") int id) {
		logger.info("Request Data==>>>>>>>" + id);

		ResponseData resp = new ResponseData();

		try {

			resp.setData(loadDroneservice.findById(id));
			resp.setResponseCode("000");
			resp.setResponseMessage("SUCCESS");

			logger.info("Response Data==>>>>>>>" + resp);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<ResponseData>(resp, HttpStatus.OK);
	}

	
	/*
	 * 
	 * 
	 * 
	 * 
	 *  checking available drones for loading;
	 * 
	 * 
	 * */
	
	
	
	
	@RequestMapping(value = { "/getAllAvailableDrones" }, method = { RequestMethod.GET }, produces = {
			"application/json" })

	public ResponseEntity<ResponseData> getAllAvailableDrones() {
		logger.info("Request Data==>>>>>>>");

		ResponseData resp = new ResponseData();
		resp.setData(droneService.findAllActiveDronesNative());
		

		try {
			droneService.findAll();
			logger.info("Response Data==>>>>>>>" + resp);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<ResponseData>(resp, HttpStatus.OK);
	}
	
	
	/*
	 * 
	 * check drone battery level for a given drone;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */

	@RequestMapping(value = { "/checkDroneBatteryByDroneId/{serialNumber}" }, method = { RequestMethod.GET }, produces = {
			"application/json" })

	public ResponseEntity<ResponseData> checkDroneBatteryByDroneId(@Valid @PathVariable("serialNumber") int serialNumber) {
		logger.info("Request Data==>>>>>>>" + serialNumber);

		ResponseData resp = new ResponseData();

		
		try {
			double baL = droneService.checkDroneBatteryByDroneId(serialNumber);
			logger.info("Battery level {}", baL);
			resp.setData(baL); 
			resp.setResponseCode("000");
			resp.setResponseMessage("SUCCESS");
			logger.info("Response Data==>>>>>>>" + resp);

		} catch (Exception ex) {
			ex.getMessage();
			logger.error(ex.toString());
			resp.setResponseCode("99");
			resp.setResponseMessage("Fail");
		}
		return new ResponseEntity<ResponseData>(resp, HttpStatus.OK);
	}
	
	


}
