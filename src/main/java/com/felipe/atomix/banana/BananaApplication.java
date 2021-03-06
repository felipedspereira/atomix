package com.felipe.atomix.banana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    basePackages = {"com.felipe.atomix.banana", "com.felipe.atomix.common.configuration"},
    excludeFilters =
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.felipe.atomix.common.configuration.client.*"))
public class BananaApplication {
  public static void main(String[] args) {
    System.setProperty("spring.config.name", "banana");
    SpringApplication.run(BananaApplication.class, args);
  }
}
