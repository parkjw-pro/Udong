package com.ssafy.udong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

	@GetMapping(value = "/admin")
	public String home( ) {
		return "/index.html";
	}
	@GetMapping(value = "/admin/detail")
	public String index() {
		return "/index.html";
	}
	@GetMapping(value = "/admin/detail/update")
	public String map() {
		return "/index.html";
	}
	@GetMapping(value = "/account/signup")
	public String about(){
		return "/index.html"; 
	}    

	@GetMapping(value = "/account")
	public String meUpdate(){
		return "/index.html"; 
	}    
	@GetMapping(value = "/account/find")
	public String notic(){
		return "/index.html"; 
	}
	@GetMapping(value = "/account/detail")
	public String a( ) {
		return "/index.html";
	}
	@GetMapping(value = "/account/update")
	public String b( ) {
		return "/index.html";
	}
	@GetMapping(value = "/badge/{userid}")
	public String c( ) {
		return "/index.html";
	}
	@GetMapping(value = "/")
	public String d( ) {
		return "/index.html";
	}
	@GetMapping(value = "/developers")
	public String e( ) {
		return "/index.html";
	}
	@GetMapping(value = "/location/first")
	public String f( ) {
		return "/index.html";
	}
	@GetMapping(value = "/location")
	public String g( ) {
		return "/index.html";
	}
	@GetMapping(value = "/home")
	public String h( ) {
		return "/index.html";
	}
	@GetMapping(value = "/news")
	public String i( ) {
		return "/index.html";
	}

	@GetMapping(value = "/store/find/{adress}/{keword}")
	public String js( ) {
		return "/index.html";
	}
	@GetMapping(value = "/store/get/{adress}")
	public String jss( ) {
		return "/index.html";
	}
	@GetMapping(value = "/reviews")
	public String j( ) {
		return "/index.html";
	}

	@GetMapping(value = "/reviews/create/{storeId}")
	public String m( ) {
		return "/index.html";
	}
	@GetMapping(value = "/reviews/{category}")
	public String n( ) {
		return "/index.html";
	}
	@GetMapping(value = "/reviews/detail/{storeId}")
	public String o( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/article/{groupId}/{groupcheck}")
	public String p( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/group/create")
	public String q( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/group")
	public String r( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/group/{groupId}/member/{groupcheck}")
	public String s( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/group/{groupId}")
	public String t( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/group/{groupId}/profile/{groupcheck}")
	public String u( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/group/{groupId}/update")
	public String v( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/feed/{userId}/{nickname}")
	public String w( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/{address}/{userId}")
	public String x( ) {
		return "/index.html";
	}
}

