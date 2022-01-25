package com.felipe.atomix.grape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    basePackages = {"com.felipe.atomix.grape", "com.felipe.atomix.common.configuration"},
    excludeFilters =
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.felipe.atomix.common.configuration.client.*"))
public class GrapeApplication {
  public static void main(String[] args) {
    System.setProperty("spring.config.name", "grape");
    SpringApplication.run(GrapeApplication.class, args);
  }
}
