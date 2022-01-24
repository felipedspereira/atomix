package com.felipe.atomix.orange.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.atomix.core.map.AtomicMap;
import io.atomix.utils.time.Versioned;

@RestController
@RequestMapping("orange")
public class TestResource {

  @Autowired private AtomicMap<String, String> distributedMap;

  @GetMapping("read")
  public ResponseEntity<String> read(@RequestParam String key) {
    String value = Versioned.valueOrElse(distributedMap.get(key), null);
    return ResponseEntity.status(HttpStatus.OK).body(value);
  }
}
