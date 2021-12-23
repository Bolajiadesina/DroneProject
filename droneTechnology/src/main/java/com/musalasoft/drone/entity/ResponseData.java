package com.musalasoft.drone.entity;

import lombok.Data;

@Data
public class ResponseData {
	
	private String responseCode;
	private String responseMessage;
    private Object data;
    
    

}
