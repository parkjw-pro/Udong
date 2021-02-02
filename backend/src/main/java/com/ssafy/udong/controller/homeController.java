//package com.ssafy.udong.controller;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class homeController {
//	
//	//유저
//	@GetMapping(value = "/admin")
//	public String admin( ) {
//		return "/index.html";
//	}
//	
//	@GetMapping(value = "/admin/detail/a")
//	public String adminDetail( ) {
//		System.out.println("admin/detail");
//		return "/index.html";
//	}
//	@GetMapping(value = "/admin/update")
//	public String adminUpdate( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/account/signup")
//	public String accountSignup( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/account")
//	public String account( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/accountFind")
//	public String accountFind( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/account/detail")
//	public String accountDetail( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/account/update")
//	public String accountUpdate( ) {
//		return "/index.html";
//	}
//	//앱
//	@GetMapping(value = "/location/first")
//	public String locationFirst( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/location")
//	public String location( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/home")
//	public String home( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/badge")
//	public String badge( ) {
//		return "/index.html";
//	}
//	//리뷰
//	@GetMapping(value = "/review")
//	public String review( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/review/findstore")
//	public String reviewFindStore( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/review/getstore")
//	public String reviewGetStore( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/review/article/create")
//	public String reviewArticleCreate( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/review/article")
//	public String reviewArticle( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/review/article/detail")
//	public String reviewArticleDetail( ) {
//		return "/index.html";
//	}
//	//스토리
//	@GetMapping(value = "/story/article")
//	public String storyArticle( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/article/detail")
//	public String storyArticleDetail( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/article/update")
//	public String storyArticleUpdate( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/group")
//	public String storyGroup( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/group/list")
//	public String storyGroupList( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/group/member")
//	public String storyGroupMember( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/group/main")
//	public String storyGroupMain( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/group/profile")
//	public String storyGroupProfile( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/group/update")
//	public String storyGroupUpdate( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/myfeed")
//	public String storyMyFeed( ) {
//		return "/index.html";
//	}
//	@GetMapping(value = "/story/newsfeed")
//	public String storyNewsFeed( ) {
//		return "/index.html";
//	}
//}