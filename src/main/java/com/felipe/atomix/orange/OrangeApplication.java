package com.felipe.atomix.orange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    basePackages = {"com.felipe.atomix.orange", "com.felipe.atomix.common.configuration"},
    excludeFilters =
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.felipe.atomix.common.configuration.management.*"))
public class OrangeApplication {

  public static void main(String[] args) {
    System.setProperty("spring.config.name", "orange");
    SpringApplication.run(OrangeApplication.class, args);
  }
}
