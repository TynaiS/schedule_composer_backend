package com.example.schedule_composer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.schedule_composer.entity")
public class ScheduleComposerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleComposerApplication.class, args);
	}

}
