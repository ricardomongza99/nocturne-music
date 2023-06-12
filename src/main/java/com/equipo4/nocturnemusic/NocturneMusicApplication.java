package com.equipo4.nocturnemusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.equipo4.nocturnemusic")
public class NocturneMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(NocturneMusicApplication.class, args);
	}

}
