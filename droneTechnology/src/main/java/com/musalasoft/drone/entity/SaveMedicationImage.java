package com.musalasoft.drone.entity;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SaveMedicationImage {

	@Id
	@GeneratedValue
	private int id;
	private String image;
	private int droneId;	
    private String name;
    private double weight;
    private String code;
    

}
