package com.rookielogic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rookielogic.model.Track;

@RestController
public class HealthCheckController {

	@RequestMapping(method=RequestMethod.GET, path="/hc")
	public String healthCheck(){
		return "music world app is running...";
	}
	
}

