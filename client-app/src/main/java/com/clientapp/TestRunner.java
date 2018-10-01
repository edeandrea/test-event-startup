package com.clientapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "client-app", name = "some-property", havingValue = "A new value from config server")
public class TestRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("TestRunner ran!!!");
	}
}
