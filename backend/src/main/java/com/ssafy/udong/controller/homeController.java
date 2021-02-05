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
	@GetMapping(value = "/accountFind")
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
	@GetMapping(value = "/badge")
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
	@GetMapping(value = "/review")
	public String j( ) {
		return "/index.html";
	}
	@GetMapping(value = "/review/findstore")
	public String k( ) {
		return "/index.html";
	}
	@GetMapping(value = "/review/getstore")
	public String l( ) {
		return "/index.html";
	}
	@GetMapping(value = "/review/article/create")
	public String m( ) {
		return "/index.html";
	}
	@GetMapping(value = "/review/article")
	public String n( ) {
		return "/index.html";
	}
	@GetMapping(value = "/review/article/detail")
	public String o( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/article")
	public String p( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/article/detail")
	public String q( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/article/update")
	public String r( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/group")
	public String s( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/group/list")
	public String t( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/group/member")
	public String u( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/group/main")
	public String v( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/group/profile")
	public String w( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/group/update")
	public String x( ) {
		return "/index.html";
	}
	@GetMapping(value = "/story/myfeed")
	public String y( ) {
		return "/index.html";
	}
	
	@GetMapping(value = "/story/newsfeed")
	public String z( ) {
		return "/index.html";
	}
	


}