package com.felipe.atomix.banana.resource;

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
@RequestMapping("banana")
public class BananaResource {
  @Autowired private AtomicMap<String, String> distributedMap;

  @GetMapping("write")
  public ResponseEntity<String> write(@RequestParam String key, @RequestParam String value) {
    distributedMap.put(key, value);
    return ResponseEntity.status(HttpStatus.OK).body("ok");
  }
  
  @GetMapping("read")
  public ResponseEntity<String> read(@RequestParam String key) {
    String value = Versioned.valueOrElse(distributedMap.get(key), null);
    return ResponseEntity.status(HttpStatus.OK).body(value);
  }
}
