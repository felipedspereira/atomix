package com.felipe.atomix.strawberry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.felipe.atomix.strawberry", "com.felipe.atomix.common"})
public class StrawberryApplication {

  public static void main(String[] args) {
    System.setProperty("spring.config.name", "strawberry");
    SpringApplication.run(StrawberryApplication.class, args);
  }
}
