package com.ssafy.udong.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

	@GetMapping(value = "/")
	public String home( ) {
			return "index.html";
	}

	@GetMapping(value = "/story")
	public String index() {
		return "index.html";
	}
	@GetMapping(value = "/location")
	public String map() {
		return "index.html";
	}
	@GetMapping(value = "/about")
	public String about(){
		return "index.html"; 
	}    

	@GetMapping(value = "/meUpdate")
	public String meUpdate(){
		return "index.html"; 
	}    
	@GetMapping(value = "/notic")
	public String notic(){
		return "index.html"; 
	}    
}