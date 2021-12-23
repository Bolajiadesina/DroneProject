package com.musalasoft.drone.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.experimental.ExtensionMethod;


@Data
public class OnlyForImagesFileWrapper {
	
    private MultipartFile file;
    private int id;

}
