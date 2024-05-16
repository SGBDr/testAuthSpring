package com.example.testMongo;

import com.example.testMongo.worker.UserWoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestMongoApplication.class, args);
	}

}
