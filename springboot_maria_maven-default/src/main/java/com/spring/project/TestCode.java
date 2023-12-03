package com.spring.project;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("root"));
	}

}
