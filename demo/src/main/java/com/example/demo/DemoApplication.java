package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		List<Integer> integers = List.of(1, 3, 5);
			System.out.println(integers);
			System.out.println(integers);
			System.out.println(integers);
			System.out.println(integers);
		System.out.println(integers);
	}

}
