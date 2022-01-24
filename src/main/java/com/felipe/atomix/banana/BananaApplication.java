package com.felipe.atomix.banana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.felipe.atomix.banana", "com.felipe.atomix.common"})
public class BananaApplication {
  public static void main(String[] args) {
    System.setProperty("spring.config.name", "banana");
    SpringApplication.run(BananaApplication.class, args);
  }
}
