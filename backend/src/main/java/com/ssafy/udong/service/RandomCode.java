package com.ssafy.udong.service;

import java.util.Random;

public class RandomCode {

	public String  randomcodes() {
		
		
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(String.valueOf((char)((int) (r.nextInt(26))+ 97)));
			
		}
		for (int i = 0; i < 3; i++) {
			sb.append(String.valueOf(r.nextInt(10)));
		}
		
		
		
		String str = sb.toString();
		
		return str;
	}
}