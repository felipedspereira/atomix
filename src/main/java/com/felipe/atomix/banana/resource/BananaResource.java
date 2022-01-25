package com.felipe.atomix.banana.resource;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.atomix.core.Atomix;
import io.atomix.primitive.partition.PartitionGroup;

@RestController
@RequestMapping("banana")
public class BananaResource {

  @Autowired
  private Atomix atomix;
  
  @GetMapping("config")
  public ResponseEntity<String> config() throws JsonProcessingException {
    Collection<PartitionGroup> partitionGroups = atomix.getPartitionService().getPartitionGroups();
    
    ObjectMapper mapper = new ObjectMapper();
    return ResponseEntity.ok(mapper.writeValueAsString(partitionGroups));
  }
}
