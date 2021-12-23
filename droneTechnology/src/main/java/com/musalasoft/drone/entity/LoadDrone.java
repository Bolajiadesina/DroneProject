package com.musalasoft.drone.entity;




import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class LoadDrone {

	
//	- name (allowed only letters, numbers, ‘-‘, ‘_’);
//	- weight;
//	- code (allowed only upper case letters, underscore and numbers);
//	- image (picture of the medication case).

	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true)
	private String image;
	@Column(unique = true)
	private int droneId;	
    private String name;
    private double weight;
    @Column(unique = true)
    private String code;

}
