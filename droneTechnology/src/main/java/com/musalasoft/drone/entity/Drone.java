package com.musalasoft.drone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity

public class Drone {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Size(min = 1, message = "{validation.name.size.too_short}")
	@Size(max = 100, message = "{validation.name.size.too_long}")
	@Column(name="serial_number")
	private int serialNumber;

	@Column(unique = true)
	private String model;
	
	@DecimalMin("0.00") 
	@DecimalMax("500.00")
	
	private double weight;
	

	@Column(name="battery_capacity")
	private double batteryCapacity;
	
	private String state;
	
	
	

	


}
