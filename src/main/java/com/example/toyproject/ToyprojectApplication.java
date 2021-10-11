package com.example.toyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToyprojectApplication {

  public static void main(String[] args) {
    SpringApplication.run(ToyprojectApplication.class, args);
  }

}
