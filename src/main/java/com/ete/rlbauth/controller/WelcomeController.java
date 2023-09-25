package com.ete.rlbauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/greet")
	public String greetingSecurity() {
		return "Welcome to Role based password encoder authentication";
		
	}

}
